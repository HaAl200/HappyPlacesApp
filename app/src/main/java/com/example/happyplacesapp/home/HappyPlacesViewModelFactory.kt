package com.example.happyplacesapp.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.happyplacesapp.components.persistence.HappyPlacesRepository

class HappyPlacesViewModelFactory(
    private val repository: HappyPlacesRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HappyPlacesViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return HappyPlacesViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
