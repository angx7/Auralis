package com.example.cameratest.ui.screens.sessions.components.models

data class HistoryUiState(
    val isLoading: Boolean = false,
    val monthTotalTime: String = "--",
    val monthAvgPrecision: String = "--",
    val monthSessionsCount: String = "--",
    val sessions: List<SessionHistoryItem> = emptyList(),
    val errorMessage: String? = null
)
