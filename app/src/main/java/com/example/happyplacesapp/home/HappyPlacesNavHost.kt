import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.BlendMode.Companion.Screen
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.happyplacesapp.home.AddPlaceScreen
import com.example.happyplacesapp.home.HappyPlacesViewModel

@Composable
fun HappyPlacesNavHost(viewModel: HappyPlacesViewModel) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Screen.Map.route) {
        composable(Screen.Map.route) {
            MapScreen(
                viewModel = viewModel,
                onAddNewPlace = { navController.navigate(Screen.AddPlace.route) },
                onShowPlacesList = { navController.navigate(Screen.PlacesList.route) }
            )
        }
        composable(Screen.AddPlace.route) {
            AddPlaceScreen(
                onPlaceSaved = { place ->
                    viewModel.addPlace(place)
                    navController.popBackStack()
                },
                onCancel = { navController.popBackStack() }
            )
        }
        composable(Screen.PlacesList.route) {
            PlacesListScreen(
                placesFlow = viewModel.placesFlow,
                onBack = { navController.popBackStack() }
            )
        }
    }
}
