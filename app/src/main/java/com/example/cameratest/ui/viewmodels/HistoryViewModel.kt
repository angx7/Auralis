package com.example.cameratest.ui.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.cameratest.data.services.RetrofitInstance
import com.example.cameratest.data.services.UserPrefs
import com.example.cameratest.models.PracticeSession
import com.example.cameratest.ui.screens.sessions.components.models.HistoryUiState
import com.example.cameratest.ui.screens.sessions.components.models.SessionHistoryItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.Locale
import kotlin.math.roundToInt

class HistoryViewModel(
    application: Application
) : AndroidViewModel(application) {

    private val app = getApplication<Application>()
    private val historyService = RetrofitInstance.historyService
    private val prefs = UserPrefs

    private val _uiState = MutableStateFlow(HistoryUiState())
    val uiState: StateFlow<HistoryUiState> = _uiState.asStateFlow()

    fun loadHistory() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(
                isLoading = true,
                errorMessage = null
            )

            try {
                val token = prefs.getAuthTokenOnce(app)
                if (token.isBlank()) {
                    _uiState.value = HistoryUiState(
                        isLoading = false,
                        errorMessage = "Sesión expirada. Inicia sesión de nuevo."
                    )
                    return@launch
                }

                val authHeader = "Bearer $token"

                // 1) Resumen mensual
                val summaryResponse = historyService.getMonthlySummary(authHeader)

                // 2) Todas las sesiones
                val sessionsResponse = historyService.getAllSessions(authHeader)

                val monthTotalTime = summaryResponse.totalHours  // ya viene como "0h 2m"
                val monthAvgPrecision = "${summaryResponse.precisionPromedio}%"
                val monthSessionsCount = summaryResponse.sesiones.toString()

                val uiSessions = sessionsResponse.sessions
                    .orEmpty()
                    .sortedByDescending { it.fechaSesion } // del más reciente al más viejo, si quieres
                    .mapIndexed { index, session ->
                        session.toHistoryItem(index)
                    }

                _uiState.value = HistoryUiState(
                    isLoading = false,
                    monthTotalTime = monthTotalTime,
                    monthAvgPrecision = monthAvgPrecision,
                    monthSessionsCount = monthSessionsCount,
                    sessions = uiSessions,
                    errorMessage = null
                )

            } catch (e: Exception) {
                e.printStackTrace()
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    errorMessage = e.message ?: "Error al cargar historial"
                )
            }
        }
    }

    // Mapper de PracticeSession (API) -> SessionHistoryItem (UI)
    private fun PracticeSession.toHistoryItem(index: Int): SessionHistoryItem {
        val localeEs = Locale("es", "MX")

        // Parseo de la ISO 8601: "2025-12-05T06:18:31.690Z"
        val instant = try {
            Instant.parse(this.fechaSesion)
        } catch (e: Exception) {
            null
        }

        val zoned = instant?.atZone(ZoneId.systemDefault())

        // Día tipo "03"
        val day = zoned?.format(DateTimeFormatter.ofPattern("dd", localeEs)) ?: "--"

        // Mes corto tipo "Dic"
        val monthRaw = zoned?.format(DateTimeFormatter.ofPattern("MMM", localeEs)) ?: "--"
        val monthShort = monthRaw.replaceFirstChar { it.uppercase(localeEs) }

        // Hora "HH:mm"
        val time = zoned?.format(DateTimeFormatter.ofPattern("HH:mm", localeEs)) ?: "--:--"

        // Título: Sesión de Práctica: <Artista> - <Titulo>
        val songTitle = this.song.titulo
        val songArtist = this.song.artista
        val title = "Sesión de Práctica: $songArtist - $songTitle"

        // Duración: viene en duration_seconds -> "45m", "2m", etc.
        val minutes = durationSeconds / 60
        val duration = "${minutes}m"

        // Precisión: si hay análisis de audio usamos precision_ritmica, si no, puntaje_total
        val precisionValue = analisisAudioOpcional?.precisionRitmica?.roundToInt()
            ?: puntajeTotal
        val precisionText = "${precisionValue}%"

        // IA Score: por ejemplo puntaje_total sobre 10 → "8.5/10"
        val iaScoreNumber = puntajeTotal / 10.0
        val iaScoreText = String.format(Locale.getDefault(), "%.1f/10", iaScoreNumber)

        return SessionHistoryItem(
            id = index,       // si luego quieres usar el _id de Mongo, cambiamos a String
            day = day,
            monthShort = monthShort,
            time = time,
            title = title,
            duration = duration,
            precision = precisionText,
            iaScore = iaScoreText
        )
    }
}