package com.example.wanderbook.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class UserDb(
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,
    @ColumnInfo(name = "email")
    var mail: String,
    @ColumnInfo(name = "login")
    var login: String,
    @ColumnInfo(name = "latitude")
    var latitude: Double, // Широта
    @ColumnInfo(name = "longitude")
    var longitude: Double // Долгота

)
