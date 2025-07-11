package com.example.happyplacesapp.components.persistence

data class HappyPlace(
    val id: Long = 0,
    val name: String,
    val description: String,
    val latitude: Double,
    val longitude: Double,
    val photoUri: String? = null,
    val notes: String = ""
)