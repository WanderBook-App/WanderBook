package com.example.wanderbook.model

data class RegisterRequest(
    val username: String,
    val email: String,
    val password: String
)