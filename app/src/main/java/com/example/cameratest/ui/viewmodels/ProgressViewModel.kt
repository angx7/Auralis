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

class ProgressViewModel(
    application: Application
) : AndroidViewModel(application){
    private val app = application
    private val aiService = RetrofitInstance.aiService
    private val prefs = UserPrefs

    var isLoading by mutableStateOf(false)
        private set

    var resumen by mutableStateOf("")
        private set

    var precisionGlobal by mutableStateOf<Double?>(null)
        private set

    var precisionActual by mutableStateOf<Int?>(null)
        private set

    var tendenciaPrecision by mutableStateOf("")
        private set

    var graficaPrecision by mutableStateOf<List<Float>>(emptyList())
        private set

    var ritmoEstableNivel by mutableStateOf("")
        private set

    var dinamicaPuntaje by mutableStateOf<Double?>(null)
        private set

    var message by mutableStateOf("")
        private set

    fun loadReport() {
        viewModelScope.launch {
            try {
                isLoading = true
                message = ""

                val token = prefs.getAuthTokenOnce(app)
                if (token.isEmpty()) {
                    message = "Token expirado o no encontrado. Inicia sesión de nuevo."
                    isLoading = false
                    return@launch
                }

                val response = aiService.getReport("Bearer $token")

                if (response.ok && response.report != null) {
                    val r = response.report
                    resumen = r.resumen
                    precisionGlobal = r.precisionGlobal
                    precisionActual = r.precisionActual
                    tendenciaPrecision = r.tendenciaPrecision
                    graficaPrecision = r.graficaPrecision.map { it.toFloat() }
                    ritmoEstableNivel = r.ritmoEstableNivel
                    dinamicaPuntaje = r.dinamicaPuntaje
                    message = ""
                } else {
                    message = response.message ?: "No se pudo obtener el reporte de progreso."
                }

            } catch (e: Exception) {
                println(e)
                message = "Error al obtener el reporte de progreso."
            } finally {
                isLoading = false
            }
        }
    }
}