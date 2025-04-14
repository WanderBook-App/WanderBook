package com.example.wanderbook.data.local

import androidx.room.Entity
import androidx.room.ForeignKey

@Entity(
    tableName = "usersBooks",
    primaryKeys = ["userId", "bookId"],
    foreignKeys = [
        ForeignKey(entity = UserDb::class, parentColumns = ["id"], childColumns = ["userId"], onDelete = ForeignKey.CASCADE),
        ForeignKey(entity = Book::class, parentColumns = ["id"], childColumns = ["bookId"], onDelete = ForeignKey.CASCADE)
    ]
)
data class UsersBooks(
    val userId: Int,
    val bookId: Int
)