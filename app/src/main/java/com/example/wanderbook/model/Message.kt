package com.example.wanderbook.model

data class Message( // TODO
    val id: String,
    val text: String,
    val timestamp: Long = 1234325,
    val isSentByMe: Boolean = true
)
