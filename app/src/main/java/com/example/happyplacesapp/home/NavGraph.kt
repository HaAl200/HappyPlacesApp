package com.example.happyplacesapp.home

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun HappyPlacesNavHost(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "places_list") {
        composable("places_list") {
            val viewModel: HappyPlacesViewModel = viewModel()
            val places by viewModel.placesFlow.collectAsState(initial = emptyList())
            PlacesListScreen(
                places = places,
                placesFlow = viewModel.placesFlow,
                onSave = { /* TODO: Implementiere speichern */ },
                onBack = { /* TODO: Implementiere zurück navigieren */ }
            )
        }
        // weitere Composables ...
    }
}