package com.example.cameratest

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.camera.core.*
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import com.example.cameratest.ui.theme.CameraTestTheme
import com.google.mediapipe.framework.image.BitmapImageBuilder
import com.google.mediapipe.tasks.components.containers.NormalizedLandmark
import com.google.mediapipe.tasks.core.BaseOptions
import com.google.mediapipe.tasks.vision.core.RunningMode
import com.google.mediapipe.tasks.vision.handlandmarker.HandLandmarker
import com.google.mediapipe.tasks.vision.handlandmarker.HandLandmarkerResult
import java.util.concurrent.Executors

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CameraTestTheme {
                CameraScreen()
            }
        }
    }
}

@Composable
fun CameraScreen() {
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current
    val cameraExecutor = remember { Executors.newSingleThreadExecutor() }

    // Permisos
    val permissions = arrayOf(Manifest.permission.CAMERA)
    var hasPermissions by remember {
        mutableStateOf(
            permissions.all {
                ContextCompat.checkSelfPermission(context, it) == PackageManager.PERMISSION_GRANTED
            }
        )
    }
    val launcher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) { res -> hasPermissions = res.values.all { it } }

    LaunchedEffect(true) {
        if (!hasPermissions) launcher.launch(permissions)
    }

    // Estados UI
    var landmarks by remember { mutableStateOf<List<List<NormalizedLandmark>>>(emptyList()) }
    var fingerCounts by remember { mutableStateOf<List<Int>>(emptyList()) }

    val preview = remember { Preview.Builder().build() }
    val analysis = remember {
        ImageAnalysis.Builder()
            .setBackpressureStrategy(ImageAnalysis.STRATEGY_KEEP_ONLY_LATEST)
            .setOutputImageFormat(ImageAnalysis.OUTPUT_IMAGE_FORMAT_RGBA_8888)
            .build()
    }

    val mainExecutor = ContextCompat.getMainExecutor(context)

    // Crear el detector de manos (2 manos)
    val landmarker = remember {
        val base = BaseOptions.builder()
            .setModelAssetPath("hand_landmarker.task")
            .build()

        val opts = HandLandmarker.HandLandmarkerOptions.builder()
            .setBaseOptions(base)
            .setRunningMode(RunningMode.LIVE_STREAM)
            .setNumHands(2) // Detecta hasta dos manos
            .setMinHandDetectionConfidence(0.5f)
            .setMinHandPresenceConfidence(0.5f)
            .setMinTrackingConfidence(0.5f)
            .setResultListener { result: HandLandmarkerResult, _ ->
                val hands = result.landmarks()
                landmarks = hands
                fingerCounts = hands.map { hand ->
                    countExtendedFingers(hand)
                }
            }
            .build()

        HandLandmarker.createFromOptions(context, opts)
    }

    // Procesar frames en tiempo real
    DisposableEffect(Unit) {
        analysis.setAnalyzer(cameraExecutor) { image ->
            try {
                val plane = image.planes[0].buffer
                val w = image.width
                val h = image.height
                val bmp = android.graphics.Bitmap.createBitmap(w, h, android.graphics.Bitmap.Config.ARGB_8888)
                plane.rewind()
                bmp.copyPixelsFromBuffer(plane)

                val rot = image.imageInfo.rotationDegrees
                val rotated = if (rot != 0) {
                    val m = android.graphics.Matrix().apply { postRotate(rot.toFloat()) }
                    android.graphics.Bitmap.createBitmap(bmp, 0, 0, w, h, m, true)
                } else bmp

                val mpImage = BitmapImageBuilder(rotated).build()
                landmarker.detectAsync(mpImage, System.currentTimeMillis())
            } catch (t: Throwable) {
                Log.e("Analyzer", "Error: ${t.message}", t)
            } finally {
                image.close()
            }
        }

        onDispose {
            analysis.clearAnalyzer()
            try { landmarker.close() } catch (_: Exception) {}
        }
    }

    // UI Compose
    Box(Modifier.fillMaxSize()) {
        if (hasPermissions) {
            // Vista previa de la cámara
            AndroidView(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(400.dp)
                    .align(Alignment.TopCenter)
                    .clip(RoundedCornerShape(16.dp)),
                factory = { ctx ->
                    val pv = androidx.camera.view.PreviewView(ctx)
                    preview.setSurfaceProvider(pv.surfaceProvider)
                    pv
                }
            )

            // Overlay con puntos de las manos
            LandmarksOverlay(
                Modifier
                    .fillMaxWidth()
                    .height(400.dp)
                    .align(Alignment.TopCenter),
                hands = landmarks
            )

            // Conteo de dedos
            Column(
                Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter)
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                if (fingerCounts.isEmpty()) {
                    Text("Sin manos detectadas")
                } else {
                    fingerCounts.forEachIndexed { index, count ->
                        Text("Mano ${index + 1}: $count dedos extendidos")
                    }
                }
                Spacer(Modifier.height(8.dp))
                Button(onClick = {
                    bindCamera(preview, analysis, lifecycleOwner)
                    Toast.makeText(context, "Procesamiento activo", Toast.LENGTH_SHORT).show()
                }) {
                    Text("Iniciar detección")
                }
            }
        } else {
            Text("Se necesita permiso de cámara", Modifier.align(Alignment.Center))
        }
    }
}

// Función para vincular la cámara
private fun bindCamera(preview: Preview, analysis: ImageAnalysis, owner: androidx.lifecycle.LifecycleOwner) {
    val context = (owner as? android.content.Context) ?: return
    val provider = ProcessCameraProvider.getInstance(context).get()
    val selector = CameraSelector.DEFAULT_BACK_CAMERA
    try {
        provider.unbindAll()
        provider.bindToLifecycle(owner, selector, preview, analysis)
    } catch (e: Exception) {
        Log.e("CameraBind", "Fallo al vincular casos de uso", e)
    }
}

// Dibuja puntos para cada mano detectada
@Composable
fun LandmarksOverlay(modifier: Modifier, hands: List<List<NormalizedLandmark>>) {
    Canvas(modifier) {
        val w = size.width
        val h = size.height
        val colors = listOf(Color.Cyan, Color.Magenta)
        hands.forEachIndexed { index, hand ->
            val color = colors[index % colors.size]
            hand.forEach {
                drawCircle(
                    color = color,
                    radius = 4f,
                    center = Offset(it.x() * w, it.y() * h)
                )
            }
        }
    }
}

// Conteo de dedos extendidos
private fun countExtendedFingers(hand: List<NormalizedLandmark>?): Int {
    if (hand == null || hand.size < 21) return 0
    val indices = listOf(8, 12, 16, 20)
    var count = 0
    for (i in indices) {
        val tip = hand[i]
        val pip = hand[i - 2]
        if (tip.y() < pip.y()) count++ // dedo extendido
    }
    // Pulgar
    val thumbTip = hand[4]
    val indexMcp = hand[5]
    if (thumbTip.x() < indexMcp.x()) count++
    return count
}
