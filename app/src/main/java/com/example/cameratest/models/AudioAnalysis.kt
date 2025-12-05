package com.example.cameratest.models

import com.google.gson.annotations.SerializedName


data class AudioAnalysis(
    @SerializedName("precision_ritmica")
    val precisionRitmica: Double,

    @SerializedName("errores_promedio_ms")
    val erroresPromedioMs: Int
)
