package com.example.cameratest.data.services

import com.example.cameratest.models.AuthResponse
import com.example.cameratest.models.LoginBody
import com.example.cameratest.models.RegisterBody
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthService {

    @POST("/auth/login")
    suspend fun login(@Body request: LoginBody): AuthResponse

    @POST("/auth/register")
    suspend fun register(@Body request: RegisterBody): AuthResponse
}