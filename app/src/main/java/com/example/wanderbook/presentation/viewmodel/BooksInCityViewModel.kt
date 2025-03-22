package com.example.wanderbook.presentation.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class BooksViewModel : ViewModel() {
    var searchText by mutableStateOf("")
        private set

    fun onSearchTextChange(newText: String) {
        searchText = newText
    }
}
