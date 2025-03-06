package com.example.wanderbook

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.example.wanderbook.presentation.ui.screens.BooksInCityScreen
import com.example.wanderbook.presentation.ui.screens.LoginScreen
import com.example.wanderbook.presentation.ui.screens.MainScreen
import com.example.wanderbook.presentation.viewmodel.AuthViewModel
import com.example.wanderbook.ui.theme.WanderBookTheme

/**
 * Основная активность приложения.
 *
 * Эта активность отвечает за отображение экрана в зависимости от состояния аутентификации пользователя.
 * При запуске проверяется, авторизован ли пользователь:
 * - Если пользователь авторизован, показывается основной экран (MainScreen).
 * - Если пользователь не авторизован, показывается экран входа (LoginScreen).
 *
 * @see AuthViewModel ViewModel, управляющая состоянием аутентификации.
 */

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val authViewModel: AuthViewModel = viewModel()

            if (authViewModel.isAuthenticated.value) {
                MainScreen()
            } else {
                LoginScreen(authViewModel = authViewModel) {
                    setContent {
                        MainScreen()
                    }
                }
            }
        }
    }
}


