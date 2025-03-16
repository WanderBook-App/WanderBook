package com.example.wanderbook.presentation.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel


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


