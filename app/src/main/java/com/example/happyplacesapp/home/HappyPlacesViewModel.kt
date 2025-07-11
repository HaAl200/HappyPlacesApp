package com.example.happyplacesapp.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.happyplacesapp.components.HappyPlace
import com.example.happyplacesapp.components.persistence.HappyPlacesRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class HappyPlacesViewModel(
    private val repository: HappyPlacesRepository
) : ViewModel() {

    val places: StateFlow<List<HappyPlace>> = repository.getAllPlaces()
        .map { it.sortedByDescending { place -> place.id } }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    fun addPlace(place: HappyPlace) {
        viewModelScope.launch {
            repository.insertPlace(place)
        }
    }

    fun deletePlace(place: HappyPlace) {
        viewModelScope.launch {
            repository.deletePlace(place)
        }
    }
}
