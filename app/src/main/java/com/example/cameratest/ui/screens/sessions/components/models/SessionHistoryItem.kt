package com.example.cameratest.ui.screens.sessions.components.models

data class SessionHistoryItem(
    val id: Int,
    val day: String,
    val monthShort: String,
    val time: String,
    val title: String,
    val duration: String,
    val precision: String,
    val iaScore: String
)
