package com.example.wanderbook.presentation.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.wanderbook.model.User


class StartViewModel : ViewModel() {
    private val _isAuthenticated = mutableStateOf(false)
    private val _isRegistered = mutableStateOf(false)
    val isAuthenticated: State<Boolean> = _isAuthenticated
    val isRegistered: State<Boolean> = _isRegistered

    private val _isLogin = mutableStateOf(false)
    val isLogin: State<Boolean> = _isLogin

    private val _isRegistr = mutableStateOf(false)
    val isRegistr: State<Boolean> = _isRegistr

    private val _isVisible = mutableStateOf(false)
    val isVisible: State<Boolean> = _isVisible

    fun setVisible() {
        _isVisible.value = true
    }

    fun auth() {
        _isAuthenticated.value = !_isAuthenticated.value
    }

    fun onRegisterButtonClick() {
        _isRegistr.value = !_isRegistr.value
    }

    fun onLoginButtonClick() {
        _isLogin.value = !_isLogin.value
    }

    var name by mutableStateOf("")
        private set

    var email by mutableStateOf("")
        private set

    var password by mutableStateOf("")
        private set

    var repeatPassword by mutableStateOf("")
        private set

    fun onNameChange(newName: String) {
        name = newName
    }

    fun onEmailChange(newEmail: String) {
        email = newEmail
    }

    fun onPasswordChange(newPassword: String) {
        password = newPassword
    }

    fun onRepeatPasswordChange(newRepeatPassword: String) {
        repeatPassword = newRepeatPassword
    }

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
        }
    }

    fun login(email: String, password: String, onResult: (Boolean) -> Unit) {
        val user = users.find { it.email == email && it.password == password }
    }
}


