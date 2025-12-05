package com.example.cameratest.models

data class AuthResponse(
    val ok : Boolean,
    val token : String?,
    val message: String?,
    val user: User?
)
