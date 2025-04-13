package com.example.wanderbook
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface Dao {
    @Insert
    fun insert(user: UserDb)

    // Обновление данных пользователя
    @Update
    suspend fun update(user: UserDb)

    // Удаление пользователя
    @Delete
    suspend fun delete(user: UserDb)

    // Поиск пользователя по ID
    @Query("SELECT * FROM UserDb WHERE id = :userId LIMIT 1")
    suspend fun getUserById(userId: Int): UserDb?

}