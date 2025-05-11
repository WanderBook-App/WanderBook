package com.example.wanderbook.presentation.viewmodel

import androidx.lifecycle.ViewModelProvider
import com.example.wanderbook.data.local.MessageDao
import androidx.lifecycle.ViewModel
import com.example.wanderbook.data.local.ChatDao
import com.example.wanderbook.presentation.viewmodel.ChatsViewModel

class MessageViewModelFactory(
    private val chatDao: ChatDao,
    private val messageDao: MessageDao
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ChatsViewModel(chatDao, messageDao) as T
    }
}