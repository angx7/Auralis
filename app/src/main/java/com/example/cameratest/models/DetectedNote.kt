package com.example.cameratest.models

import com.google.gson.annotations.SerializedName

data class DetectedNote(
    @SerializedName("tiempo_ms")
    val tiempoMs: Long,

    @SerializedName("nota_detectada")
    val notaDetectada: String
)