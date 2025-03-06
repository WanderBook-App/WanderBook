package com.example.wanderbook.presentation.ui.screens

import BottomNavigationBar
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.wanderbook.navigation.AppNavGraph

/**
 * Главный экран приложения с навигацией.
 *
 * Этот экран отображает нижнюю панель навигации и основной контент,
 * включая все экраны, определенные в навигационном графе.
 *
 * @param navController Контроллер навигации, используемый для управления навигацией между экранами.
 */

@Composable
fun MainScreen() {
    val navController = rememberNavController()

    Scaffold(
        bottomBar = { BottomNavigationBar(navController) }
    ) { innerPadding ->
        AppNavGraph(navController, Modifier.padding(innerPadding))
    }
}
