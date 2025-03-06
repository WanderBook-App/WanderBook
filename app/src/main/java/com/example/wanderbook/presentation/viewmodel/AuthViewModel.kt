package com.example.wanderbook.presentation.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

/**
 * ViewModel, управляющая процессом аутентификации пользователя.
 *
 * Эта ViewModel отвечает за отслеживание состояния аутентификации пользователя.
 * Она проверяет, авторизован ли пользователь, и предоставляет методы для входа в систему.
 * Логика аутентификации (например, проверка в SharedPreferences) может быть добавлена в будущем.
 *
 * @property isAuthenticated Состояние, показывающее, авторизован ли пользователь.
 */

class AuthViewModel : ViewModel() {
    private val _isAuthenticated = mutableStateOf(false)
    val isAuthenticated: State<Boolean> = _isAuthenticated

    init {
        _isAuthenticated.value = checkIfUserIsAuthenticated()
    }

    private fun checkIfUserIsAuthenticated(): Boolean {
        // Здесь можно добавить проверку (например, взять из SharedPreferences)
        return false // Пока что пользователь не авторизован
    }

    fun login(username: String, password: String) {
        // Здесь должна быть логика входа
        _isAuthenticated.value = true
    }
}


