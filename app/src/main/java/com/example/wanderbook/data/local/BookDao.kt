package com.example.wanderbook.data.local
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import androidx.room.OnConflictStrategy



@Dao
interface BookDao {

    @Query("SELECT * FROM local_books")
    suspend fun getAllBooks(): List<LocalBook>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBook(book: LocalBook)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBooks(books: List<LocalBook>)

    @Query("DELETE FROM local_books WHERE id = :id")
    suspend fun deleteBook(id: String)

    @Query("SELECT * FROM local_books WHERE isFavorite = 1")
    suspend fun getFavorites(): List<LocalBook>

    @Query("SELECT * FROM local_books WHERE isUserOwned = 1")
    suspend fun getUserBooks(): List<LocalBook>
}
