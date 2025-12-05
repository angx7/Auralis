package com.example.cameratest.data.services

import com.example.cameratest.models.AuthResponse
import com.example.cameratest.models.LoginBody
import com.example.cameratest.models.MeResponse
import com.example.cameratest.models.RegisterBody
import com.example.cameratest.models.UpdateProfileBody
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.PUT

interface AuthService {

    @POST("/auth/login")
    suspend fun login(@Body request: LoginBody): AuthResponse

    @POST("/auth/register")
    suspend fun register(@Body request: RegisterBody): AuthResponse

    @GET("/auth/me")
    suspend fun getMe(
        @Header("Authorization") authHeader: String
    ): MeResponse

    @PUT("/auth/profile")
    suspend fun updateProfile(
        @Header("Authorization") authHeader: String,
        @Body body: UpdateProfileBody
    ): AuthResponse
}