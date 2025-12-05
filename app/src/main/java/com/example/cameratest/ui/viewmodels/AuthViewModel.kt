package com.example.cameratest.ui.viewmodels

import android.app.Application
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.cameratest.data.services.RetrofitInstance
import com.example.cameratest.data.services.UserPrefs
import com.example.cameratest.models.LoginBody
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class AuthViewModel(
    application: Application
): AndroidViewModel(application) {
    private val app = application
    val prefs = UserPrefs
    val authService = RetrofitInstance.authService
    var message by mutableStateOf("")
    var isLogged by mutableStateOf(false)
        private set

    init {
        viewModelScope.launch {
            prefs.getIsLogged(app).collectLatest { logged ->
                isLogged = logged
            }
        }
    }

    fun login(email: String, password: String) {
        viewModelScope.launch {
            try {
                val login = LoginBody(
                    email = email,
                    password = password
                )
                val result = authService.login(login)
                if (result.ok) {
                    prefs.saveUserName(app, result.user?.username ?: "")
                    prefs.saveAuthToken(app, result.token ?: "")
                    prefs.saveIsLogged(app, true)

                    isLogged = true
                } else {
                    message = result.message ?: "Login failed"
                }
            } catch (e: Exception) {
                message = "Login failed"
                println(e)
            }
        }
    }

    fun register(userName: String, email: String, password: String) {
        viewModelScope.launch {
            try {
                val registerBody = com.example.cameratest.models.RegisterBody(
                    username = userName,
                    email = email,
                    password = password
                )
                val result = authService.register(registerBody)
                if (result.ok) {
                    prefs.saveUserName(app, result.user?.username ?: "")
                    prefs.saveAuthToken(app, result.token ?: "")
                    prefs.saveIsLogged(app, true)

                    isLogged = true
                } else {
                    message = result.message ?: "Registration failed"
                }
            } catch (e: Exception) {
                message = "Registration failed"
                println(e)
            }
        }
    }
}