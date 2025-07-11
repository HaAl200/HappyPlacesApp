package com.example.happyplacesapp.home

import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.ui.Modifier
import com.example.happyplacesapp.components.persistence.HappyPlace
import kotlinx.coroutines.flow.Flow

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PlacesListScreen(
    places: List<HappyPlace>,
    placesFlow: Flow<List<HappyPlace>>,
    onSave: () -> Unit,
    onBack: () -> Unit
) {
    Scaffold(
        topBar = { TopAppBar(title = { Text("Meine Orte") }) }
    ) { padding ->
        Column(modifier = Modifier.padding(padding)) {
            places.forEach { place ->
                Text("${place.name} - ${place.description}")
            }
            Button(onClick = onBack) { Text("Zurück") }
        }
    }
}