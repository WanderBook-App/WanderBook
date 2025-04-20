package com.example.wanderbook.data.local
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity(tableName = "local_chats")
data class LocalChat(
    @PrimaryKey val id: String, // UUID
    val user1Id: String,
    val user2Id: String,
    val lastMessage: String,
    val updatedAt: Long
)
