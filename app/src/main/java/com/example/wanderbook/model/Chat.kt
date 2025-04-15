package com.example.wanderbook.model

data class Chat( // TODO
    val id: String,
    val book: Book,
    val recipient: User,
    val messages: List<Message> = emptyList(),
    val lastMessageTimestamp: Long = System.currentTimeMillis(),
    val status: ChatStatus = ChatStatus.ACTIVE
)