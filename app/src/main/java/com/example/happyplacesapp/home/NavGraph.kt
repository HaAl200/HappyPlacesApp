package com.example.happyplacesapp.home

import HomeScreen
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.navigation.compose.*
import org.osmdroid.util.GeoPoint


@Composable
fun AppNavGraph(
    viewModel: HappyPlacesViewModel,
    selectedPoint: GeoPoint?,
    onSelectPoint: (GeoPoint) -> Unit
) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "home") {

        composable("home") {
            HomeScreen(
                places = viewModel.places.collectAsState().value,
                selectedPoint = selectedPoint,
                onPointSelected = onSelectPoint,
                onAddNewPlace = { navController.navigate("addPlace") }
            )
        }

        composable("addPlace") {
            AddPlaceScreen(
                selectedPoint = selectedPoint,
                onSave = { newPlace ->
                    viewModel.addPlace(newPlace)
                },
                onBack = { navController.popBackStack() }
            )
        }
    }
}
