package com.example.wanderbook.navigation

import com.example.wanderbook.R


sealed class NavRoutes(val route: String, val title: Int, val icon: Int? = null) {
    object Start : NavRoutes("start", 1)
    object Login : NavRoutes("login", 1)
    object Register : NavRoutes("register", 1)

    object BooksInCity : NavRoutes("books_in_city", R.string.books_in_city, R.drawable.city)
    object BooksNearby : NavRoutes("books_nearby", R.string.books_nearby, R.drawable.location)
    object MyLibrary : NavRoutes("my_library", R.string.my_library, R.drawable.book)
    object Profile : NavRoutes("profile", R.string.profile, R.drawable.person)
}

