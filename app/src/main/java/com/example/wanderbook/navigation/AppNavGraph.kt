package com.example.wanderbook.navigation

import com.example.wanderbook.presentation.ui.components.BottomNavigationBar
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.wanderbook.data.local.AppDatabase
import com.example.wanderbook.data.local.SharedPreferencesUtil
import com.example.wanderbook.presentation.ui.screens.BookDetailsScreen
import com.example.wanderbook.presentation.ui.screens.BooksInCityScreen
import com.example.wanderbook.presentation.ui.screens.BooksNearbyScreen
import com.example.wanderbook.presentation.ui.screens.MyChatsScreen
import com.example.wanderbook.presentation.ui.screens.MyProfileScreen
import com.example.wanderbook.presentation.ui.screens.StartScreen
import com.example.wanderbook.presentation.viewmodel.StartViewModel
import com.example.wanderbook.presentation.ui.screens.ChatScreen


@Composable
fun AppNavGraph(navController: NavHostController, startViewModel: StartViewModel) {
    val isAuthenticated by startViewModel.isAuthenticated
    val isRegisrtered by startViewModel.isRegistered
    if (isAuthenticated || isRegisrtered) {
        MainGraph(navController)
    } else {
        AuthGraph(navController, startViewModel)
    }
}

@Composable
fun AuthGraph(navController: NavHostController, startViewModel: StartViewModel) {
    NavHost(
        navController = navController,
        startDestination = NavRoutes.Start.route,
        enterTransition = { slideIntoContainer(
            towards = AnimatedContentTransitionScope.SlideDirection.Left,
            animationSpec = tween(700)
        ) }
    ) {
        composable(NavRoutes.Start.route) {
            StartScreen(
                onLoginClick = { startViewModel.auth() },
                onRegisterClick = { startViewModel.auth() }
            )
        }
    }
}

@Composable
fun MainGraph(navController: NavHostController) {
    val currentRoute = remember { mutableStateOf("") }
    Scaffold(
        bottomBar = {
            if (currentRoute.value != NavRoutes.BookDetails.route && currentRoute.value != "chat/{chatId}") {
            BottomNavigationBar(navController)
        } },
        topBar = {  }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = NavRoutes.BooksInCity.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(NavRoutes.BooksInCity.route) {
                currentRoute.value = NavRoutes.BooksInCity.route
                BooksInCityScreen(navController)
            }
            composable(NavRoutes.BooksNearby.route) {
                currentRoute.value = NavRoutes.BooksNearby.route
                BooksNearbyScreen()
            }
            composable(NavRoutes.Profile.route) {
                currentRoute.value = NavRoutes.Profile.route
                MyProfileScreen()
            }
            composable(NavRoutes.Chats.route) {
                currentRoute.value = NavRoutes.Chats.route
                MyChatsScreen(navController)
            }
            composable(NavRoutes.BookDetails.route + "/{bookId}") { backStackEntry ->
                currentRoute.value = NavRoutes.BookDetails.route
                val bookId = backStackEntry.arguments?.getString("bookId")?.toIntOrNull()
                if (bookId != null) {
                    BookDetailsScreen(bookId, navController)
                }
            }
//            composable("chat/{chatId}") { backStackEntry ->
//                currentRoute.value = "chat/{chatId}"
//                val chatId = backStackEntry.arguments?.getString("chatId") ?: ""
//                ChatScreen(navController = navController, chatId = chatId)
//            }
            composable("chat/{chatId}") { backStackEntry ->
                currentRoute.value = "chat/{chatId}"
                val chatId = backStackEntry.arguments?.getString("chatId") ?: ""

                // Получаем контекст
                val context = LocalContext.current

                // Получаем JWT токен из SharedPreferences
                val token = SharedPreferencesUtil.getJwtToken(context)

                // Извлекаем Id из токена
                val senderId = SharedPreferencesUtil.getSenderIdFromToken(token) ?: ""  // Если токен не найден используем пустую строку

                // Инициализируем базу данных
                val database = remember { AppDatabase.getDatabase(context) }

                // DAO
                val chatDao = database.chatDao()
                val messageDao = database.messageDao()



                // Передаём все нужные параметры
                ChatScreen(
                    navController = navController,
                    chatId = chatId,
                    senderId = senderId,
                    chatDao = chatDao,
                    messageDao = messageDao
                )
            }



        }
    }
}



