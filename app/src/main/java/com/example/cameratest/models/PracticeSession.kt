package com.example.cameratest.models

import com.google.gson.annotations.SerializedName

data class PracticeSession(
    @SerializedName("_id")
    val id: String,

    @SerializedName("user_id")
    val userId: String,

    // El backend manda "song_id" como objeto Song embebido
    @SerializedName("song_id")
    val song: Song,

    @SerializedName("fecha_sesion")
    val fechaSesion: String,  // Lo puedes parsear a Instant/LocalDateTime si luego quieres

    @SerializedName("puntaje_total")
    val puntajeTotal: Int,

    @SerializedName("duration_seconds")
    val durationSeconds: Int,

    @SerializedName("deteccion_visual")
    val deteccionVisual: VisualDetection?,

    @SerializedName("analisis_audio_opcional")
    val analisisAudioOpcional: AudioAnalysis?,

    @SerializedName("createdAt")
    val createdAt: String,

    @SerializedName("updatedAt")
    val updatedAt: String
)