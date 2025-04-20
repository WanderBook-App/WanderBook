package com.example.wanderbook.data.local
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import androidx.room.OnConflictStrategy

@Dao
interface MessageDao {

    @Query("SELECT * FROM local_messages WHERE chatId = :chatId ORDER BY sentAt ASC")
    suspend fun getMessagesForChat(chatId: String): List<LocalMessage>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMessage(message: LocalMessage)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMessages(messages: List<LocalMessage>)
}
