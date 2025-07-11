package com.example.happyplacesapp

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.Looper
import com.example.happyplacesapp.components.OSMMapWithMarkers
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresPermission
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.content.ContextCompat
import com.example.happyplacesapp.components.HappyPlace
import com.example.happyplacesapp.ui.theme.HappyPlacesAppTheme
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.Priority
import org.osmdroid.util.GeoPoint

class MainActivity : ComponentActivity() {

    private val permissions = arrayOf(
        android.Manifest.permission.ACCESS_FINE_LOCATION,
        android.Manifest.permission.ACCESS_COARSE_LOCATION,
    )

    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private lateinit var locationCallback: LocationCallback
    private var locationRequired: Boolean = false

    override fun onPause() {
        super.onPause()
        fusedLocationClient.removeLocationUpdates(locationCallback)
    }

    override fun onResume() {
        super.onResume()
        if (locationRequired) {
            startLocationUpdates()
        }
    }

    @RequiresPermission(allOf = [Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION])
    private fun startLocationUpdates() {
        val locationRequest = LocationRequest.Builder(
            Priority.PRIORITY_HIGH_ACCURACY, 100
        )
            .setWaitForAccurateLocation(false)
            .setMinUpdateIntervalMillis(3000)
            .setMaxUpdateDelayMillis(100)
            .build()
        fusedLocationClient.requestLocationUpdates(
            locationRequest,
            locationCallback,
            Looper.getMainLooper()
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

        enableEdgeToEdge()
        setContent {
            var currentLocation by remember {
                mutableStateOf(GeoPoint(0.0, 0.0))
            }

            locationCallback = object : LocationCallback() {
                override fun onLocationResult(p0: LocationResult) {
                    super.onLocationResult(p0)
                    for (location in p0.locations) {
                        currentLocation = GeoPoint(location.latitude, location.longitude)
                    }
                }
            }

            HappyPlacesAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    // Beispiel-Liste
                    val places = listOf(
                        HappyPlace(1, "Test-Ort", "Beschreibung", "", currentLocation.latitude, currentLocation.longitude)
                    )
                    OSMMapWithMarkers(context = this@MainActivity, happyPlaces = places)
                }
            }
        }
    }

    @Composable
    private fun LocationScreen(context: Context, currentLocation: GeoPoint) {
        val launcherMultiplePermissions = androidx.activity.compose.rememberLauncherForActivityResult(
            contract = ActivityResultContracts.RequestMultiplePermissions()
        ) { permissionMaps ->
            val areGranted = permissionMaps.values.reduce { acc, next -> acc && next }
            if (areGranted) {
                locationRequired = true
                startLocationUpdates()
                Toast.makeText(context, "Permission Granted", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(context, "Permission Denied", Toast.LENGTH_SHORT).show()
            }
        }

        Box(modifier = Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Bottom,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = "Your Location: ${currentLocation.latitude}, ${currentLocation.longitude}")
                Button(onClick = {
                    if (permissions.all {
                            ContextCompat.checkSelfPermission(context, it) == PackageManager.PERMISSION_GRANTED
                        }) {
                        // Get Location
                        startLocationUpdates()
                    } else {
                        launcherMultiplePermissions.launch(permissions)
                    }
                }) {
                    Text(text = "Get your Location")
                }
            }
        }
    }

    @Preview(showBackground = true)
    @Composable
    private fun LocationScreenPreview() {
        // Achtung: "this" ist im Preview-Kontext ungültig!
        // LocationScreen(context = LocalContext.current, currentLocation = GeoPoint(0.0, 0.0))
    }
}