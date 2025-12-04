package com.example.cameratest.ui.viewmodels

import android.app.Application
import android.graphics.Bitmap
import android.graphics.Matrix
import android.util.Log
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageProxy
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.google.mediapipe.framework.image.BitmapImageBuilder
import com.google.mediapipe.tasks.components.containers.NormalizedLandmark
import com.google.mediapipe.tasks.core.BaseOptions
import com.google.mediapipe.tasks.vision.core.RunningMode
import com.google.mediapipe.tasks.vision.handlandmarker.HandLandmarker
import com.google.mediapipe.tasks.vision.handlandmarker.HandLandmarkerResult
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CameraViewModel(application: Application) : AndroidViewModel(application), ImageAnalysis.Analyzer {

    private val _landmarks = MutableStateFlow<List<List<NormalizedLandmark>>>(emptyList())
    val landmarks = _landmarks.asStateFlow()

    private val _fingerCounts = MutableStateFlow<List<Int>>(emptyList())
    val fingerCounts = _fingerCounts.asStateFlow()

    private var handLandmarker: HandLandmarker

    init {
        handLandmarker = createHandLandmarker()
    }

    private fun createHandLandmarker(): HandLandmarker {
        val baseOptions = BaseOptions.builder()
            .setModelAssetPath("hand_landmarker.task")
            .build()

        val options = HandLandmarker.HandLandmarkerOptions.builder()
            .setBaseOptions(baseOptions)
            .setRunningMode(RunningMode.LIVE_STREAM)
            .setNumHands(2)
            .setMinHandDetectionConfidence(0.5f)
            .setMinHandPresenceConfidence(0.5f)
            .setMinTrackingConfidence(0.5f)
            .setResultListener { result, _ -> processHandLandmarkerResult(result) }
            .build()

        return HandLandmarker.createFromOptions(getApplication(), options)
    }

    private fun processHandLandmarkerResult(result: HandLandmarkerResult) {
        val hands = result.landmarks()
        _landmarks.value = hands
        _fingerCounts.value = hands.map { hand ->
            countExtendedFingers(hand)
        }
    }

    override fun analyze(image: ImageProxy) {
        viewModelScope.launch {
            try {
                val bitmap = image.toBitmap()

                val matrix = Matrix().apply {
                    postRotate(image.imageInfo.rotationDegrees.toFloat())
                }

                val rotatedBitmap = Bitmap.createBitmap(
                    bitmap, 0, 0, bitmap.width, bitmap.height, matrix, true
                )

                val mpImage = BitmapImageBuilder(rotatedBitmap).build()
                handLandmarker.detectAsync(mpImage, System.currentTimeMillis())

            } catch (t: Throwable) {
                Log.e("CameraViewModel", "Error analyzing image: ", t)
            } finally {
                image.close()
            }
        }
    }

    private fun countExtendedFingers(hand: List<NormalizedLandmark>?): Int {
        if (hand == null || hand.size < 21) return 0
        val indices = listOf(8, 12, 16, 20) // Puntas de los dedos índice, medio, anular y meñique
        var count = 0

        // Lógica para los 4 dedos (índice, medio, anular, meñique) en horizontal
        // Asumimos que los dedos apuntan hacia la derecha de la pantalla
        // Un dedo está extendido si la coordenada X de su punta es mayor que la de su base.
        for (i in indices) {
            val tip = hand[i]
            val pip = hand[i - 2] // Articulación inferior
            if (tip.x() > pip.x()) {
                count++
            }
        }

        // Lógica para el pulgar en horizontal
        // Con la mano en horizontal, el pulgar se extiende "hacia arriba".
        // Un pulgar está extendido si la coordenada Y de su punta es menor que la de su base.
        val thumbTip = hand[4]
        val thumbPip = hand[3]
        if (thumbTip.y() < thumbPip.y()) {
            count++
        }

        return count
    }

    override fun onCleared() {
        super.onCleared()
        try {
            handLandmarker.close()
        } catch (e: Exception) {
            Log.e("CameraViewModel", "Error closing landmarker", e)
        }
    }
}
