package com.example.wanderbook.data.local
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "local_messages")
data class LocalMessage(
    @PrimaryKey val id: String, // UUID
    val chatId: String,
    val senderId: String,
    val text: String,
    val sentAt: Long, // timestamp
    val isSynced: Boolean = false
)
