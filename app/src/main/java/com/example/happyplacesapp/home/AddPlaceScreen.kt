package com.example.happyplacesapp.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.happyplacesapp.components.HappyPlace
import org.osmdroid.util.GeoPoint

@Composable
fun AddPlaceScreen(
    onSave: (HappyPlace) -> Unit,
    selectedPoint: GeoPoint?,
    onBack: () -> Unit
) {
    var title by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    val context = LocalContext.current

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp)) {

        Text(text = "Neuen Ort hinzufügen", modifier = Modifier.padding(bottom = 8.dp))

        BasicTextField(
            value = title,
            onValueChange = { title = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            decorationBox = { innerTextField ->
                Box(Modifier.padding(4.dp)) {
                    if (title.isEmpty()) Text("Titel eingeben")
                    innerTextField()
                }
            }
        )

        BasicTextField(
            value = description,
            onValueChange = { description = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            decorationBox = { innerTextField ->
                Box(Modifier.padding(4.dp)) {
                    if (description.isEmpty()) Text("Beschreibung eingeben")
                    innerTextField()
                }
            }
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                val place = HappyPlace(
                    id = 0, // Wird von Room gesetzt
                    title = title,
                    description = description,
                    image = "", // Optional: Bild später hinzufügen
                    latitude = selectedPoint?.latitude ?: 0.0,
                    longitude = selectedPoint?.longitude ?: 0.0
                )
                onSave(place)
                onBack()
            },
            enabled = title.isNotBlank() && description.isNotBlank() && selectedPoint != null
        ) {
            Text("Speichern")
        }

        Spacer(modifier = Modifier.height(8.dp))

        Button(onClick = onBack) {
            Text("Abbrechen")
        }
    }
}
