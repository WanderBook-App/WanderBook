package com.example.wanderbook

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.example.wanderbook.navigation.AppNavGraph
import com.example.wanderbook.presentation.viewmodel.StartViewModel


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        setContent {
            val startViewModel: StartViewModel = viewModel()
            val navController = rememberNavController()
            AppNavGraph(navController, startViewModel)
        }
    }
}



