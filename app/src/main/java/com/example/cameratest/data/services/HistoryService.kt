package com.example.cameratest.data.services

import com.example.cameratest.models.MonthlySummaryResponse
import com.example.cameratest.models.SessionHistoryResponse
import retrofit2.http.GET
import retrofit2.http.Header

interface HistoryService {
        @GET("sessions/month")
    suspend fun getMonthlySummary(
        @Header("Authorization") authHeader: String
    ): MonthlySummaryResponse

    // GET /sessions
    @GET("sessions")
    suspend fun getAllSessions(
        @Header("Authorization") authHeader: String
    ): SessionHistoryResponse
}