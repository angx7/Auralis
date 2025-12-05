package com.example.cameratest.ui.screens.sessions.components.models

import com.example.cameratest.ui.screens.sessions.PracticeDifficulty

data class PracticePiece(
    val id: String,
    val title: String,
    val composer: String,
    val difficulty: PracticeDifficulty,
    val dateLabel: String,
    val coverUrl: String? = null
)
