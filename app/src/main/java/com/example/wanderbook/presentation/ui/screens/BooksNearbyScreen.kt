package com.example.wanderbook.presentation.ui.screens

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.wanderbook.data.local.AppDatabase
import com.example.wanderbook.presentation.viewmodel.BooksViewModel
import com.example.wanderbook.presentation.viewmodel.BooksViewModelFactory

@Composable
fun BooksNearbyScreen() {

    val context = LocalContext.current
    val db = remember { AppDatabase.getDatabase(context) }
    val viewModel: BooksViewModel = viewModel(
        factory = BooksViewModelFactory(db.bookDao())
    )
    Text("Nearby")

}