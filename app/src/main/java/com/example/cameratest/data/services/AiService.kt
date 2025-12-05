package com.example.cameratest.data.services

import com.example.cameratest.models.AiProTipResponse
import retrofit2.http.Header
import retrofit2.http.POST

interface AiService {

    @POST("/ai/protip")
    suspend fun getProTip(
        @Header("Authorization") authHeader: String
    ): AiProTipResponse
}