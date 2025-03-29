package com.example.wanderbook.model

data class Book(
    val id: Int,
    val title: String,
    val author: String,
    val genre: String,
    val bookcrosser_id: Int,
    val condition: String,
    val description: String,
    val coverResId: Int
)
