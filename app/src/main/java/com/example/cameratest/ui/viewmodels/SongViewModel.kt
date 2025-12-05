package com.example.cameratest.ui.viewmodels

import android.app.Application
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.cameratest.data.services.RetrofitInstance
import com.example.cameratest.data.services.UserPrefs
import com.example.cameratest.models.Song
import com.example.cameratest.ui.screens.sessions.PracticeDifficulty
import com.example.cameratest.ui.screens.sessions.components.models.PracticePiece
import kotlinx.coroutines.launch

class SongViewModel(
    application: Application
) : AndroidViewModel(application){
    private val app = application
    private val prefs = UserPrefs
    private val songsService = RetrofitInstance.songsService

    var allPieces by mutableStateOf<List<PracticePiece>>(emptyList())
        private set

    var isLoading by mutableStateOf(false)
        private set

    var errorMessage by mutableStateOf<String?>(null)
        private set

    fun loadSongs() {
        viewModelScope.launch {
            try {
                isLoading = true
                errorMessage = null

                val token = prefs.getAuthTokenOnce(app)
                if (token.isEmpty()) {
                    errorMessage = "Token vencido. Por favor, inicia sesión de nuevo."
                    allPieces = emptyList()
                    return@launch
                }

                val response = songsService.getSongs("Bearer $token")

                if (response.ok && response.songs != null) {
                    allPieces = response.songs.map { it.toPracticePiece() }
                } else {
                    errorMessage = response.message ?: "No se pudieron cargar las canciones."
                    allPieces = emptyList()
                }

            } catch (e: Exception) {
                errorMessage = "Error al cargar canciones."
                allPieces = emptyList()
                println(e)
            } finally {
                isLoading = false
            }
        }
    }

    private fun Song.toPracticePiece(): PracticePiece {
        return PracticePiece(
            id = _id,
            title = titulo,
            composer = artista,
            difficulty = mapDifficulty(dificultad),
            dateLabel = formatIsoDate(createdAt),
            coverUrl = recursos.imagenUrl
        )
    }

    private fun mapDifficulty(apiDifficulty: String): PracticeDifficulty =
        when (apiDifficulty.lowercase()) {
            "facil" -> PracticeDifficulty.EASY
            "intermedio" -> PracticeDifficulty.MEDIUM
            "dificil" -> PracticeDifficulty.HARD
            else -> PracticeDifficulty.ALL
        }

    private fun formatIsoDate(iso: String): String {
        return iso.take(10)
    }

}