package com.example.cameratest.data.services

import com.example.cameratest.models.SongsResponse
import retrofit2.http.GET
import retrofit2.http.Header

interface SongService {
    @GET("/songs")
    suspend fun getSongs(
        @Header("Authorization") authHeader: String
    ): SongsResponse
}