package com.example.wanderbook.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.wanderbook.presentation.ui.screens.BooksInCityScreen
import com.example.wanderbook.presentation.ui.screens.BooksNearbyScreen
import com.example.wanderbook.presentation.ui.screens.LoginScreen
import com.example.wanderbook.presentation.ui.screens.MyLibraryScreen
import com.example.wanderbook.presentation.ui.screens.ProfileScreen

/**
 * Основной граф навигации приложения.
 *
 * Создает `NavHost`, который управляет переходами между экранами.
 * Навигация начинается после авторизации через `AuthViewModel` с экрана "Книги в городе".
 * Остальные экраны добавляются в `addMainScreens`.
 *
 * @param navController Контроллер навигации, управляющий переходами между экранами.
 */

@Composable
fun AppNavGraph(navController: NavHostController, modifier: Modifier = Modifier) {
    NavHost(
        navController = navController,
        startDestination = NavRoutes.BooksInCity.route,
        modifier = modifier
    ) {
        addMainScreens(navController)
    }
}

/**
 * Добавляет основные экраны приложения в навигационный граф.
 *
 * @param navController Контроллер навигации, используемый для управления переходами.
 */

private fun NavGraphBuilder.addMainScreens(navController: NavController) {
    composable(NavRoutes.BooksInCity.route) { BooksInCityScreen() }
    composable(NavRoutes.BooksNearby.route) { BooksNearbyScreen() }
    composable(NavRoutes.MyLibrary.route) { MyLibraryScreen() }
    composable(NavRoutes.Profile.route) { ProfileScreen() }
}
