package com.example.happyplacesapp.components.persistence

import com.example.happyplacesapp.components.HappyPlace
import com.example.happyplacesapp.components.data.HappyPlaceDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class HappyPlacesRepository(private val dao: HappyPlaceDao) {

    fun getAllPlaces(): Flow<List<HappyPlace>> = dao.getAllPlaces()

    suspend fun insertPlace(place: HappyPlace) {
        dao.insertPlace(place)
    }

    suspend fun deletePlace(place: HappyPlace) {
        dao.deletePlace(place)
    }
}

