package com.example.wanderbook.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database (entities = [UserDb::class, Book::class, UsersBooks::class], version = 1)
abstract class MainDb: RoomDatabase() {
    abstract class getDao(): Dao
    companion object{
        fun getDb(context: Context): MainDb{
            return Room.databaseBuilder(
                context.applicationContext,
                MainDb::class.java,
                "Book.db"

            ).build()
        }
    }
}