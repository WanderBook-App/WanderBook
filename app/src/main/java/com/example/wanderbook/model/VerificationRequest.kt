package com.example.wanderbook.model

data class VerificationRequest(
    val email: String,
    val verificate_code: String
)