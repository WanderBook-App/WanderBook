package com.example.wanderbook

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

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