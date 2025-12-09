package com.example.cameratest.ui.viewmodels

import android.content.Context
import android.graphics.*
import android.media.Image
import android.util.Log
import android.view.View
import androidx.annotation.OptIn
import androidx.camera.core.*
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.core.content.ContextCompat
import com.google.mediapipe.framework.image.BitmapImageBuilder
import com.google.mediapipe.tasks.core.BaseOptions
import com.google.mediapipe.tasks.vision.core.RunningMode
import com.google.mediapipe.tasks.vision.handlandmarker.HandLandmarker
import com.google.mediapipe.tasks.vision.handlandmarker.HandLandmarker.HandLandmarkerOptions
import com.google.mediapipe.tasks.vision.handlandmarker.HandLandmarkerResult
import kotlin.concurrent.thread
import com.example.cameratest.ui.screens.camera.PianoDetector
import kotlin.math.max
import com.example.cameratest.models.DetectedKey

class CameraProcessor(
    private val context: Context,
    private val previewView: PreviewView,
    private val pianoView: PianoView,
    private val pianoDetector: PianoDetector
) {

    private lateinit var handLandmarker: HandLandmarker

    // ============================================================
    //               CONFIGURACIÓN DEL DETECTOR DE MANO
    // ============================================================

    fun setupHandDetector() {
        val baseOptions = BaseOptions.builder()
            .setModelAssetPath("hand_landmarker.task")
            .build()

        val options = HandLandmarkerOptions.builder()
            .setBaseOptions(baseOptions)
            .setNumHands(1)
            .setRunningMode(RunningMode.LIVE_STREAM)
            .setResultListener { result: HandLandmarkerResult, _ ->
                handleHandResult(result)
            }
            .setErrorListener { e ->
                Log.e("HandLandmarker", "Error: ${e.message}", e)
            }
            .build()

        handLandmarker = HandLandmarker.createFromOptions(context, options)
    }

    // ============================================================
    //               INICIO DE CÁMARA
    // ============================================================

    fun startCamera() {
        val cameraProviderFuture = ProcessCameraProvider.getInstance(context)

        cameraProviderFuture.addListener({
            val cameraProvider = cameraProviderFuture.get()

            val preview = Preview.Builder().build().apply {
                setSurfaceProvider(previewView.surfaceProvider)
            }

            val analysis = ImageAnalysis.Builder()
                .setBackpressureStrategy(ImageAnalysis.STRATEGY_KEEP_ONLY_LATEST)
                .build()

            analysis.setAnalyzer(
                ContextCompat.getMainExecutor(context)
            ) { imageProxy ->
                processImage(imageProxy)
            }

            val selector = CameraSelector.DEFAULT_BACK_CAMERA

            cameraProvider.unbindAll()
            cameraProvider.bindToLifecycle(
                context as androidx.lifecycle.LifecycleOwner,
                selector,
                preview,
                analysis
            )

        }, ContextCompat.getMainExecutor(context))
    }

    // ============================================================
    //               PROCESAMIENTO DE IMAGEN POR FRAME
    // ============================================================

    @OptIn(ExperimentalGetImage::class)
    private fun processImage(imageProxy: ImageProxy) {
        val bitmap = imageProxyToBitmap(imageProxy)

        thread {
            if (bitmap != null) {

                // === IA de mano ===
                val mpImage = BitmapImageBuilder(bitmap).build()
                handLandmarker.detectAsync(mpImage, imageProxy.imageInfo.timestamp)

                // === Detección de piano ===
                val detectedRect = pianoDetector.detectPianoRect(bitmap)

                if (detectedRect != null) {
                    Log.i("PianoDetector", "Piano detectado: $detectedRect")

                    val mappedRect = mapRectToPreviewView(bitmap, previewView, detectedRect)

                    pianoView.post {
                        pianoView.updateDetectedPianoRect(mappedRect)
                    }

                    val keys = pianoDetector.detectKeys(bitmap, detectedRect)
                    val mappedKeys = keys.map { key ->
                        val mapped = mapRectToPreviewView(
                            bitmap,
                            previewView,
                            Rect(
                                key.rect.left.toInt(),
                                key.rect.top.toInt(),
                                key.rect.right.toInt(),
                                key.rect.bottom.toInt()
                            )
                        )

                        key.copy(rect = RectF(
                            mapped.left.toFloat(),
                            mapped.top.toFloat(),
                            mapped.right.toFloat(),
                            mapped.bottom.toFloat()
                        ))
                    }

// Actualizar Vista
                    pianoView.post {
                        pianoView.updateDetectedKeys(mappedKeys)
                    }
                }
            }

            imageProxy.close()
        }
    }

    // ============================================================
    //               MEDIAPIPE → MANO DETECTADA
    // ============================================================

    private fun handleHandResult(result: HandLandmarkerResult) {
        if (result.landmarks().isEmpty()) return

        val hand = result.landmarks()[0]  // Mano 0

        // PUNTO DEL DEDO: índice tip (8)
        val indexTip = hand[8]

        // Normalizado [0–1] → convertir a px del previewView
        val x = indexTip.x() * previewView.width
        val y = indexTip.y() * previewView.height

        pianoView.post {
            pianoView.updateFingerPosition(x, y)
        }
    }

    // ============================================================
    //               CONVERSIÓN: ImageProxy → Bitmap
    // ============================================================

    private fun imageProxyToBitmap(imageProxy: ImageProxy): Bitmap? {
        val image = imageProxy.image ?: return null

        val yBuffer = imageProxy.planes[0].buffer // Y
        val uBuffer = imageProxy.planes[1].buffer // U
        val vBuffer = imageProxy.planes[2].buffer // V

        val ySize = yBuffer.remaining()
        val uSize = uBuffer.remaining()
        val vSize = vBuffer.remaining()

        val nv21 = ByteArray(ySize + uSize + vSize)

        yBuffer.get(nv21, 0, ySize)
        vBuffer.get(nv21, ySize, vSize)
        uBuffer.get(nv21, ySize + vSize, uSize)

        val yuvImage = YuvImage(
            nv21,
            ImageFormat.NV21,
            imageProxy.width,
            imageProxy.height,
            null
        )

        val out = java.io.ByteArrayOutputStream()
        yuvImage.compressToJpeg(
            Rect(0, 0, imageProxy.width, imageProxy.height),
            90,
            out
        )
        val imageBytes = out.toByteArray()
        return BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.size)
    }
}

private fun mapRectToPreviewView(
    bitmap: Bitmap,
    preview: PreviewView,
    rect: Rect
): Rect {

    val bmpW = bitmap.width.toFloat()
    val bmpH = bitmap.height.toFloat()

    val viewW = preview.width.toFloat()
    val viewH = preview.height.toFloat()

    val scale = max(viewW / bmpW, viewH / bmpH)

    // dimensiones finales que ocupa el frame escalado
    val scaledW = bmpW * scale
    val scaledH = bmpH * scale

    // offsets EXACTOS que usa CameraX con CENTER_CROP
    val offsetX = (viewW - scaledW) / 2f
    val offsetY = (viewH - scaledH) / 2f

    return Rect(
        (rect.left * scale + offsetX).toInt(),
        (rect.top * scale + offsetY).toInt(),
        (rect.right * scale + offsetX).toInt(),
        (rect.bottom * scale + offsetY).toInt()
    )
}




