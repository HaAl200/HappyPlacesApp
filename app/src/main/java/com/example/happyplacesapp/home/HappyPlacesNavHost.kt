package com.example.happyplacesapp.home

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun HappyPlacesNavHost(
    viewModel: HappyPlacesViewModel,
    navController: NavHostController
) {
    NavHost(navController = navController, startDestination = Screen.PlacesList.route) {
        composable(Screen.PlacesList.route) {
            val places by viewModel.placesFlow.collectAsState(initial = emptyList())
            PlacesListScreen(
                places = places,
                placesFlow = viewModel.placesFlow,
                onSave = { },
                onBack = { navController.popBackStack() }
            )
        }
        // Weitere composable-Routen je nach Bedarf...
    }
}