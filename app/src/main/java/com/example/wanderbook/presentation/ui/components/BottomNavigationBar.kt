package com.example.wanderbook.presentation.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.wanderbook.navigation.NavRoutes


@Composable
fun BottomNavigationBar(navController: NavController) {
    val items = listOf(NavRoutes.BooksInCity, NavRoutes.BooksNearby, NavRoutes.MyLibrary, NavRoutes.Profile)

    val currentRoute by navController.currentBackStackEntryAsState()

    NavigationBar {
        items.forEach { screen ->
            NavigationBarItem(
                selected = currentRoute?.destination?.route == screen.route,
                onClick = {
                    navController.navigate(screen.route) {
                        launchSingleTop = true
                        restoreState = true
                        popUpTo(NavRoutes.BooksInCity.route) { saveState = true }
                    }
                },
                icon = {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Top
                    ) {
                        screen.icon?.let { painterResource(id = it) }?.let {
                            Icon(
                                painter = it,
                                contentDescription = stringResource(screen.title),
                                modifier = Modifier
                                    .size(24.dp)
                            )
                        }
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = stringResource(screen.title),
                            textAlign = TextAlign.Center,
                            fontSize = 12.sp
                        )
                    }
                }
            )
        }
    }
}





