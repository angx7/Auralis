package com.example.cameratest.ui.viewmodels

import android.app.Application
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.cameratest.data.services.RetrofitInstance
import com.example.cameratest.data.services.UserPrefs
import kotlinx.coroutines.launch

class HomeViewModel(
    application: Application
) : AndroidViewModel(application) {
    private val app = application
    private val aiService = RetrofitInstance.aiService
    var tipText by mutableStateOf("")
        private set
    var message by mutableStateOf("")
        private set
    val prefs = UserPrefs
    val username = prefs.getUserName(app)
    var isLoadingProTip by mutableStateOf(false)

    init {
        viewModelScope.launch {
            // Puedes realizar alguna acción inicial si es necesario
            loadProTip()
        }
    }

    fun loadProTip() {
        viewModelScope.launch {
            try {
                tipText = ""
                message = ""
                isLoadingProTip = true
                // 1) Leer token desde DataStore
                val token = UserPrefs.getAuthTokenOnce(app)

                if (token.isEmpty()) {
                    message = "No hay token guardado"
                    return@launch
                }

                // 2) Llamar endpoint con header Authorization
                val response = aiService.getProTip("Bearer $token")

                if (response.ok && response.tip != null) {
                    isLoadingProTip = false
                    tipText = response.tip
                    message = ""
                } else {
                    isLoadingProTip = false
                    message = response.tip ?: "No se pudo obtener el tip"
                }
            } catch (e: Exception) {
                isLoadingProTip = false
                tipText = "Error al obtener el tip"
                message = "Error al obtener el tip"
                println(e)
            }
        }
    }

    fun logout(){
        viewModelScope.launch {
            prefs.clear(app)
        }
    }
}