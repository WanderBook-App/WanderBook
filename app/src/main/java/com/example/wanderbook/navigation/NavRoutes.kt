package com.example.wanderbook.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.wanderbook.R

/**
 * Определяет маршруты навигации в приложении.
 *
 * Каждому экрану соответствует объект внутри `sealed class`, содержащий:
 * - `route` — строковый идентификатор маршрута.
 * - `title` — ресурс строки для заголовка экрана.
 * - `icon` — ресурс изображения для иконки экрана.
 */

sealed class NavRoutes(val route: String, val title: Int, val icon: Int) {
    object BooksInCity : NavRoutes("books_in_city", R.string.books_in_city, R.drawable.city)
    object BooksNearby : NavRoutes("books_nearby", R.string.books_nearby, R.drawable.location)
    object MyLibrary : NavRoutes("my_library", R.string.my_library, R.drawable.book)
    object Profile : NavRoutes("profile", R.string.profile, R.drawable.person)
}
