package com.example.wanderbook.presentation.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.wanderbook.model.User


class AuthViewModel : ViewModel() {
    private val _isAuthenticated = mutableStateOf(false)
    private val _isRegistered = mutableStateOf(false)
    val isAuthenticated: State<Boolean> = _isAuthenticated
    val isRegistered: State<Boolean> = _isRegistered

    // список пользователей
    private val users = mutableListOf<User>(
        User("Dasha", "dasha@mail.ru", "123"),
        User("Dima", "dima@mail.ru", "123"),
    )

    fun register(username: String, email: String, password: String, onResult: (Boolean) -> Unit) {
        // Проверяем, есть ли уже пользователь с таким email
        if (users.any { it.email == email }) {
            onResult(false) // Email уже используется
        } else {
            users.add(User(username, email, password)) // Добавляем пользователЯ
            _isRegistered.value = true
            onResult(true) // Успешная регистрация
        }
    }


    fun login(email: String, password: String, onResult: (Boolean) -> Unit) {
        val user = users.find { it.email == email && it.password == password }
        _isAuthenticated.value = true
        onResult(user != null) // true = успешный вход, false = ошибка
    }
}


