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
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.wanderbook.presentation.ui.screens.BookDetailsScreen
import com.example.wanderbook.presentation.ui.screens.BooksInCityScreen
import com.example.wanderbook.presentation.ui.screens.BooksNearbyScreen
import com.example.wanderbook.presentation.ui.screens.MyLibraryScreen
import com.example.wanderbook.presentation.ui.screens.MyProfileScreen
import com.example.wanderbook.presentation.ui.screens.StartScreen
import com.example.wanderbook.presentation.viewmodel.StartViewModel


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
            if (currentRoute.value != NavRoutes.BookDetails.route) {
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
            composable(NavRoutes.MyLibrary.route) {
                currentRoute.value = NavRoutes.MyLibrary.route
                MyLibraryScreen()
            }
            composable(NavRoutes.Profile.route) {
                currentRoute.value = NavRoutes.Profile.route
                MyProfileScreen()
            }
            composable(NavRoutes.BookDetails.route + "/{bookId}") { backStackEntry ->
                currentRoute.value = NavRoutes.BookDetails.route
                val bookId = backStackEntry.arguments?.getString("bookId")?.toIntOrNull()
                if (bookId != null) {
                    BookDetailsScreen(bookId, navController)
                }
            }
        }
    }
}



