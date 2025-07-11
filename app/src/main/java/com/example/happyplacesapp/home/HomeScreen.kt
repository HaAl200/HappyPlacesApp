import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.happyplacesapp.components.HappyPlace
import com.example.happyplacesapp.components.OSMMapWithMarkers
import org.osmdroid.util.GeoPoint

@Composable
fun HomeScreen(
    places: List<HappyPlace>,
    selectedPoint: GeoPoint?,
    onPointSelected: (GeoPoint) -> Unit,
    onAddNewPlace: () -> Unit,
) {
    val context = LocalContext.current

    Column(modifier = Modifier.fillMaxSize()) {
        Box(modifier = Modifier.weight(1f)) {
            OSMMapWithMarkers(
                context = context,
                happyPlaces = places,
                onMapTap = { geoPoint -> onPointSelected(geoPoint) },
                selectedPoint = selectedPoint
            )
        }

        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .padding(8.dp)
        ) {
            items(places) { place ->
                PlaceListItem(place = place, onClick = {
                    onPointSelected(GeoPoint(place.latitude, place.longitude))
                })
            }
        }

        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            onClick = onAddNewPlace
        ) {
            Text("Neuen Ort hinzufügen")
        }
    }
}

@Composable
fun PlaceListItem(place: HappyPlace, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
            .clickable { onClick() },
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = place.title, style = MaterialTheme.typography.titleMedium)
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = place.description, style = MaterialTheme.typography.bodyMedium)
        }
    }
}
