package com.example.cameratest.models

data class Song(
    val _id: String,
    val titulo: String,
    val artista: String,
    val dificultad: String,
    val recursos: SongResources,
    val createdAt: String,
    val updatedAt: String
)