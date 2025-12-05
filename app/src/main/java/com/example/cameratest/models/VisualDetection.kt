package com.example.cameratest.models

import com.google.gson.annotations.SerializedName

data class VisualDetection(
    @SerializedName("notas_incorrectas_visual")
    val notasIncorrectasVisual: Int,

    @SerializedName("secuencia_detectada")
    val secuenciaDetectada: List<DetectedNote>
)
