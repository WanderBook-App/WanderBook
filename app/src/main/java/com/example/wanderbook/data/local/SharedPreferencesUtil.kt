package com.example.wanderbook.data.local

import android.content.Context
import android.content.SharedPreferences
import com.auth0.jwt.JWT



object SharedPreferencesUtil {
    private const val PREFS_NAME = "wanderbook_prefs"
    private const val JWT_KEY = "jwt_token"

    fun saveJwtToken(context: Context, token: String) {
        val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        prefs.edit().putString(JWT_KEY, token).apply()
    }

    fun getJwtToken(context: Context): String? {
        val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        return prefs.getString(JWT_KEY, null)
    }

    fun clearJwtToken(context: Context) {
        val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        prefs.edit().remove(JWT_KEY).apply()
    }

    // Извлекаем senderId из JWT токена
    fun getSenderIdFromToken(token: String?): String? {
        return try {
            token?.let {
                val jwt = com.auth0.android.jwt.JWT(it)
                jwt.getClaim("sub").asString()
            }
        } catch (e: Exception) {
            null
        }
    }
}