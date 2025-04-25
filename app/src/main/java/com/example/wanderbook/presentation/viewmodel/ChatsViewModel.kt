package com.example.wanderbook.presentation.viewmodel


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.wanderbook.data.local.ChatDao
import com.example.wanderbook.data.local.LocalChat
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

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
