package com.example.cameratest.models

data class MeResponse(
    val ok: Boolean,
    val user: User?,
    val message: String?
)