package com.example.wanderbook.presentation.viewmodel


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.wanderbook.data.local.ChatDao
import com.example.wanderbook.data.local.LocalChat
import com.example.wanderbook.model.Message
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.util.UUID

class ChatsViewModel(private val chatDao: ChatDao) : ViewModel() {

    private val _chats = MutableStateFlow<List<LocalChat>>(emptyList())
    val chats: StateFlow<List<LocalChat>> = _chats

    init {
        loadChats()
    }

    private fun loadChats() {
        viewModelScope.launch {
            _chats.value = chatDao.getAllChats()
        }
    }
}

class FakeChatsViewModel : ViewModel() {
    private val _messages = MutableStateFlow<List<Message>>(
        listOf(
            Message("1", "Привет!", isSentByMe = false),
            Message("2", "Привет, как дела?", isSentByMe = true),
            Message("3", "Всё отлично, спасибо!", isSentByMe = false),
        )
    )
    val messages: StateFlow<List<Message>> = _messages

    fun sendMessage(text: String) {
        if (text.isNotBlank()) {
            val newMessage = Message(
                id = UUID.randomUUID().toString(),
                text = text,
                timestamp = System.currentTimeMillis(),
                isSentByMe = true
            )
            _messages.value = _messages.value + newMessage
        }
    }
}
