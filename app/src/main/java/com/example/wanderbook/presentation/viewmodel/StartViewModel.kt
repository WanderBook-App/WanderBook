package com.example.wanderbook.presentation.viewmodel

import android.app.Application
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.wanderbook.model.User

import androidx.lifecycle.viewModelScope
import com.example.wanderbook.model.LoginRequest
import com.example.wanderbook.data.local.remote.RetrofitInstance
import kotlinx.coroutines.launch

import android.util.Log
import androidx.lifecycle.AndroidViewModel
import com.example.wanderbook.data.local.SharedPreferencesUtil
import com.example.wanderbook.model.RegisterRequest
import com.example.wanderbook.model.VerificationRequest
import com.example.wanderbook.model.VerificationResponse


class StartViewModel(application: Application) : AndroidViewModel(application) {
    private val _isAuthenticated = mutableStateOf(false)
    private val _isRegistered = mutableStateOf(false)
    val isAuthenticated: State<Boolean> = _isAuthenticated
    val isRegistered: State<Boolean> = _isRegistered

    private val _loginError = mutableStateOf<String?>(null)
    val loginError: State<String?> = _loginError

    private val _isLogin = mutableStateOf(false)
    val isLogin: State<Boolean> = _isLogin

    private val _isRegistr = mutableStateOf(false)
    val isRegistr: State<Boolean> = _isRegistr

    private val _isVisible = mutableStateOf(false)
    val isVisible: State<Boolean> = _isVisible

    var code by mutableStateOf("")
        private set

    fun onCodeChange(newCode: String) {
        code = newCode
    }

    var isCodeSent = mutableStateOf(false)
        private set

    fun onCodeSent() {
        isCodeSent.value = true
        _isLogin.value = false
        _isRegistr.value = false
    }


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
    private val authApi = RetrofitInstance.api

    // список пользователей
    private val users = mutableListOf<User>(
        User("Dasha", "dasha@mail.ru", "123"),
        User("Dima", "dima@mail.ru", "123"),
    )


    fun register(username: String, email: String, password: String, onResult: (Boolean) -> Unit) { viewModelScope.launch {
            Log.d("RegisterDebug", "Отправка запроса на сервер: username=$username, email=$email")
            try {
                val response = RetrofitInstance.api.register(RegisterRequest(username, email, password))
                Log.d("RegisterDebug", "Ответ сервера: ${response.code()} - ${response.body()}")

                if (response.isSuccessful) {
                    _loginError.value = null
                    onResult(true)
                } else {
                    _loginError.value = "Ошибка регистрации"
                    Log.w("RegisterDebug", "Ошибка регистрации: ${response.errorBody()?.string()}")
                    onResult(false)
                }
            } catch (e: Exception) {
                e.printStackTrace()
                Log.e("RegisterError", "Ошибка при выполнении запроса: ${e.message}")
                _loginError.value = "Ошибка подключения к серверу"
                onResult(false)
            }
        }
    }


    fun verifyEmail(email: String, code: String, onResult: (Boolean) -> Unit) {
        viewModelScope.launch {
            Log.d("VerifyDebug", "Отправка запроса на сервер: email=$email, code=$code")
            try {
                val response = RetrofitInstance.api.verify(VerificationRequest(email, code))

                Log.d("VerifyDebug", "Ответ сервера: ${response.code()} - ${response.body()}")

                if (response.isSuccessful) {
                    val body = response.body()
                    Log.d("VerifyDebug", "Сообщение от сервера: ${response.body()?.message}")

                    _isAuthenticated.value = true
                    _loginError.value = null
                    onResult(true)
                } else {
                    _loginError.value = "Неверный код подтверждения"
                    Log.w("VerifyDebug", "Ошибка верификации: ${response.errorBody()?.string()}")
                    onResult(false)
                }
            } catch (e: Exception) {
                e.printStackTrace()
                Log.e("VerifyError", "Ошибка при выполнении запроса: ${e.message}")
                _loginError.value = "Ошибка подключения к серверу"
                onResult(false)
            }
        }
    }



    fun login(email: String, password: String, onResult: (Boolean) -> Unit) {
        viewModelScope.launch {
            Log.d("LoginDebug", " Отправка запроса на сервер: $email / $password")
            try {
                val response = RetrofitInstance.api.login(LoginRequest(email, password))
                Log.d("LoginDebug", "Ответ сервера: ${response.code()} - ${response.body()?.access_token}")

                if (response.isSuccessful) {
                    val token = response.body()?.access_token
                    if (token != null) {
                        SharedPreferencesUtil.saveJwtToken(getApplication(), token)
                    }

                    _isAuthenticated.value = true
                    _loginError.value = null
                    onResult(true)
                } else {
                    _loginError.value = "Неверный логин или пароль"
                    Log.w("LoginDebug", " Ошибка авторизации: ${response.errorBody()?.string()}")
                    onResult(false)
                }
            } catch (e: Exception) {
                e.printStackTrace()
                Log.e("LoginError", " Ошибка при выполнении запроса: ${e.message}")
                _loginError.value = "Ошибка подключения к серверу"
                onResult(false)
            }
        }
    }
}


