package com.example.happyplacesapp.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.happyplacesapp.components.persistence.HappyPlace
import com.example.happyplacesapp.components.persistence.HappyPlacesRepository
import kotlinx.coroutines.launch

class HappyPlacesViewModel(
    private val repository: HappyPlacesRepository
) : ViewModel() {

    val placesFlow = repository.getAllPlaces()

    fun addPlace(place: HappyPlace) {
        viewModelScope.launch {
            repository.insertPlace(place)
        }
    }

    // Beispielmethoden für Hinzufügen, Aktualisieren, Löschen
    // suspend fun addPlace(place: HappyPlace) = repository.insertPlace(place)
    // suspend fun deletePlace(place: HappyPlace) = repository.deletePlace(place)
    // usw.
}