package com.example.cameratest.models

data class MonthlySummaryResponse(
    val ok: Boolean,
    val totalSeconds: Int,
    val totalMinutes: Int,
    val totalHours: String,
    val precisionPromedio: Int,
    val sesiones: Int,
    val message: String? = null
)
