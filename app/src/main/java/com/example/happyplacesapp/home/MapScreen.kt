package com.example.happyplacesapp.home

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun MapScreen(
    viewModel: HappyPlacesViewModel,
    onAddNewPlace: () -> Unit,
    onShowPlacesList: () -> Unit
) {
    // Hier müsste OSM-Map eingebunden werden, z.B. mit osmdroid
    // Die gespeicherten Orte (viewModel.placesFlow) können als Marker angezeigt werden.
    Text("Karte mit Orten (OSM Map hier einbinden)")
}