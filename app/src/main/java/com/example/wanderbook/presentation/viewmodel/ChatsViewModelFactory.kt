package com.example.wanderbook.presentation.viewmodel


import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.wanderbook.data.local.ChatDao
import com.example.wanderbook.data.local.MessageDao

class ChatsViewModelFactory(
    private val chatDao: ChatDao,
    private val messageDao: MessageDao
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ChatsViewModel::class.java)) {
            return ChatsViewModel(chatDao, messageDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
