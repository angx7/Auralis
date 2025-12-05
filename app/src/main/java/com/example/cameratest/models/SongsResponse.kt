package com.example.cameratest.models

data class SongsResponse(
    val ok: Boolean,
    val songs: List<Song>?,
    val message: String? = null
)