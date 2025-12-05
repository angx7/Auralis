package com.example.cameratest.models

data class AiReportResponse(
    val ok: Boolean,
    val report: AiReport?,
    val message: String? = null,
    val error: String? = null
)