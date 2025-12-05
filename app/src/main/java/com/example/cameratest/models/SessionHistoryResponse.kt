package com.example.cameratest.models

data class SessionHistoryResponse(
    val ok: Boolean,
    val sessions: List<PracticeSession>?,
    val message: String? = null
)
