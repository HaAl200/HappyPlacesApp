package com.example.happyplacesapp.components

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "happyplaces")
data class HappyPlace(
    val id: Int,
    val title: String,
    val description: String,
    val image: String,
    val latitude: Double,
    val longitude: Double,
    val note: String? = null   // Optionales Notizfeld
)
