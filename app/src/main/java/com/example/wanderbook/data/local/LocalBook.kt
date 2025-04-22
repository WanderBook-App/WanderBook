package com.example.wanderbook.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "local_books")
data class LocalBook(
    @PrimaryKey val id: String, // UUID
    val title: String,
    val author: String,
    val genre: String,
    val condition: String,
    val description: String,
    val coverUrl: String,
    val isFavorite: Boolean = false,
    val lat: Double? = null,
    val lng: Double? = null,
    val isUserOwned: Boolean = false,
    val isActive: Boolean = true,
    val updatedAt: Long // timestamp
)

