package com.example.cameratest.data.services

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

private val Context.dataStore by preferencesDataStore(name  = "user_prefs")

object UserPrefs {
    // 2) Claves
    private val KEY_USER_NAME   = stringPreferencesKey("userName")
    private val KEY_IS_LOGGED = booleanPreferencesKey("isLogged")
    private val KEY_TOKEN     = stringPreferencesKey("authToken")
    private val KEY_STREAK      = intPreferencesKey("loginStreak")

    // ================
    // GUARDAR
    // ================
    suspend fun saveUserName(context: Context, userName: String) {
        context.dataStore.edit { prefs ->
            prefs[KEY_USER_NAME] = userName
        }
    }

    suspend fun saveIsLogged(context: Context, isLogged: Boolean) {
        context.dataStore.edit { prefs ->
            prefs[KEY_IS_LOGGED] = isLogged
        }
    }

    suspend fun saveAuthToken(context: Context, token: String) {
        context.dataStore.edit { prefs ->
            prefs[KEY_TOKEN] = token
        }
    }

    suspend fun saveStreak(context: Context, streak: Int) {
        context.dataStore.edit { prefs ->
            prefs[KEY_STREAK] = streak
        }
    }

    // ================
    // LEER (Flow)
    // ================
    fun getUserName(context: Context): Flow<String> =
        context.dataStore.data.map { prefs ->
            prefs[KEY_USER_NAME] ?: ""
        }

    fun getIsLogged(context: Context): Flow<Boolean> =
        context.dataStore.data.map { prefs ->
            prefs[KEY_IS_LOGGED] ?: false
        }

    fun getAuthToken(context: Context): Flow<String> =
        context.dataStore.data.map { prefs ->
            prefs[KEY_TOKEN] ?: ""
        }

    fun getStreak(context: Context): Flow<Int> =
        context.dataStore.data.map { prefs ->
            prefs[KEY_STREAK] ?: 0
        }

    suspend fun getAuthTokenOnce(context: Context): String {
        return getAuthToken(context).first()
    }

    // ================
    // LIMPIAR
    // ================
    suspend fun clear(context: Context) {
        context.dataStore.edit { prefs ->
            prefs.clear()
        }
    }
}