import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.happyplacesapp.components.HappyPlace
import kotlinx.coroutines.flow.StateFlow

@Composable
fun PlacesListScreen(
    placesFlow: StateFlow<List<HappyPlace>>,
    onBack: () -> Unit
) {
    val places by placesFlow.collectAsState()

    Column {
        Text("Gespeicherte Orte", style = MaterialTheme.typography.titleLarge, modifier = Modifier.padding(16.dp))

        LazyColumn {
            items(places) { place ->
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(place.title, style = MaterialTheme.typography.titleMedium)
                    Text(place.description, style = MaterialTheme.typography.bodySmall)
                    Text("Notiz: ${place.note}", style = MaterialTheme.typography.bodySmall)
                }
            }
        }

        Button(onClick = onBack, modifier = Modifier.align(Alignment.CenterHorizontally).padding(16.dp)) {
            Text("Zurück")
        }
    }
}
