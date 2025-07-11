package com.example.happyplacesapp.home

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.*
import androidx.compose.material.*
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.ui.platform.LocalContext
import com.example.happyplacesapp.components.persistence.HappyPlace

@Composable
fun AddPlaceScreen(
    onPlaceSaved: (HappyPlace) -> Unit,
    onCancel: () -> Unit
) {
    var name by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var notes by remember { mutableStateOf("") }

    // Dummy Koordinaten, in Praxis per Map auswählen!
    var latitude by remember { mutableStateOf(0.0) }
    var longitude by remember { mutableStateOf(0.0) }
    // Dummy Foto-URI
    var photoUri by remember { mutableStateOf<String?>(null) }

    Column {
        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Name") }
        )
        OutlinedTextField(
            value = description,
            onValueChange = { description = it },
            label = { Text("Beschreibung") }
        )
        OutlinedTextField(
            value = notes,
            onValueChange = { notes = it },
            label = { Text("Notizen") }
        )

        Button(onClick = {
            onPlaceSaved(
                HappyPlace(
                    name = name,
                    description = description,
                    latitude = latitude,
                    longitude = longitude,
                    photoUri = photoUri,
                    notes = notes
                )
            )
        }) {
            Text("Ort speichern")
        }
        Button(onClick = onCancel) {
            Text("Abbrechen")
        }
    }
}