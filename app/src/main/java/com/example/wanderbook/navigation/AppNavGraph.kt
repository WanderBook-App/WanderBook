package com.example.wanderbook.navigation

import com.example.wanderbook.presentation.ui.components.BottomNavigationBar
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.wanderbook.presentation.ui.screens.BooksInCityScreen
import com.example.wanderbook.presentation.ui.screens.BooksNearbyScreen
import com.example.wanderbook.presentation.ui.screens.LoginScreen
import com.example.wanderbook.presentation.ui.screens.MyLibraryScreen
import com.example.wanderbook.presentation.ui.screens.ProfileScreen
import com.example.wanderbook.presentation.ui.screens.RegistrationScreen
import com.example.wanderbook.presentation.ui.screens.StartScreen
import com.example.wanderbook.presentation.viewmodel.AuthViewModel


@Composable
fun AppNavGraph(navController: NavHostController, authViewModel: AuthViewModel) {
    val isAuthenticated by authViewModel.isAuthenticated
    val isRegisrtered by authViewModel.isRegistered
    if (isAuthenticated || isRegisrtered) {
        MainGraph(navController)
    } else {
        AuthGraph(navController, authViewModel)
    }
}

@Composable
fun AuthGraph(navController: NavHostController, authViewModel: AuthViewModel) {
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
                onLoginClick = { navController.navigate(NavRoutes.Login.route) },
                onRegisterClick = { navController.navigate(NavRoutes.Register.route) }
            )
        }
        composable(NavRoutes.Login.route) {
            LoginScreen(authViewModel) {
                authViewModel.login("email", "pass") { } // Симуляция входа
            }
        }
        composable(NavRoutes.Register.route) {
            RegistrationScreen(authViewModel) {
                authViewModel.register("new_user", "new_email","pass") { }// Симуляция входа
            }
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
                ProfileScreen()
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
