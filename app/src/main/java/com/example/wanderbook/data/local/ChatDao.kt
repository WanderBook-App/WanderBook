package com.example.wanderbook.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import androidx.room.OnConflictStrategy

@Dao
interface ChatDao {

    @Query("SELECT * FROM local_chats ORDER BY updatedAt DESC")
    suspend fun getAllChats(): List<LocalChat>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertChat(chat: LocalChat)
}
