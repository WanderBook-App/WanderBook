package com.example.wanderbook.presentation.viewmodel


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.wanderbook.data.local.ChatDao
import com.example.wanderbook.data.local.LocalChat
import com.example.wanderbook.data.local.LocalMessage
import com.example.wanderbook.data.local.MessageDao
import com.example.wanderbook.model.Message
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.util.UUID

class ChatsViewModel(
    private val chatDao: ChatDao,
    private val messageDao: MessageDao
) : ViewModel() {

    private val _chats = MutableStateFlow<List<LocalChat>>(emptyList())
    val chats: StateFlow<List<LocalChat>> = _chats

    private val _messages = MutableStateFlow<List<LocalMessage>>(emptyList())
    val messages: StateFlow<List<LocalMessage>> = _messages

    init {
        loadChats()
    }

    private fun loadChats() {
        viewModelScope.launch {
            _chats.value = chatDao.getAllChats()
        }
    }

    fun loadMessagesForChat(chatId: String) {
        viewModelScope.launch {
            _messages.value = messageDao.getMessagesForChat(chatId)
        }
    }

    fun sendMessage(chatId: String, senderId: String, text: String) {
        if (text.isBlank()) return

        val message = LocalMessage(
            id = UUID.randomUUID().toString(),
            chatId = chatId,
            senderId = senderId,
            text = text,
            sentAt = System.currentTimeMillis()
        )

        viewModelScope.launch {
            messageDao.insertMessage(message)
            loadMessagesForChat(chatId) // обновляем список
        }
    }
}
