package com.example.cameratest.ui.viewmodels

import android.app.Application
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.cameratest.data.services.RetrofitInstance
import com.example.cameratest.data.services.UserPrefs
import com.example.cameratest.models.UpdateProfileBody
import kotlinx.coroutines.NonCancellable
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class EditUserViewModel(
    application: Application
) : AndroidViewModel(application) {
    private val app = application
    private val prefs = UserPrefs
    private val authService = RetrofitInstance.authService
    var username by mutableStateOf("")
        private set

    var email by mutableStateOf("")
        private set

    var message by mutableStateOf("")
        private set
    var isLoading by mutableStateOf(false)
        private set
    var updateSuccess by mutableStateOf(false)
        private set

    fun onUsernameChange(newValue: String) {
        username = newValue
    }

    fun onEmailChange(newValue: String) {
        email = newValue
    }

    fun loadUserProfile() {
        viewModelScope.launch {
            try {
                isLoading = true
                val token = prefs.getAuthTokenOnce(app)
                if (token.isEmpty()) {
                    message = "No auth token found. Please log in again."
                    isLoading = false
                    return@launch
                }

                val response = authService.getMe("Bearer $token")

                if (response.ok && response.user != null) {
                    username = response.user.username
                    email = response.user.email
                    message = ""
                } else {
                    message = response.message ?: "Could not load profile"
                }
            } catch (e: Exception) {
                message = "Error loading profile"
                println(e)
            } finally {
                isLoading = false
            }
        }
    }

    fun updateProfile() {
        viewModelScope.launch {
            try {
                isLoading = true
                val token = prefs.getAuthTokenOnce(app)
                if (token.isEmpty()) {
                    message = "No auth token found. Please log in again."
                    isLoading = false
                    return@launch
                }

                val body = UpdateProfileBody(
                    newUsername = username,
                    newEmail = email
                )

                val response = authService.updateProfile(
                    authHeader = "Bearer $token",
                    body = body
                )

                if (response.ok && response.user != null) {
                    username = response.user.username
                    email = response.user.email
                    withContext(NonCancellable) {
                        prefs.saveUserName(app, username)
                    }
                    message = response.message ?: "Profile updated"
                    updateSuccess = true
                } else {
                    message = response.message ?: "Profile update failed"
                    updateSuccess = false
                }
            } catch (e: Exception) {
                message = "Profile update failed"
                updateSuccess = false
                println(e)
            } finally {
                isLoading = false
            }
        }
    }
}