package com.example.happyplacesapp.components

import android.content.Context
import android.view.ViewGroup
import androidx.compose.runtime.Composable
import androidx.compose.ui.viewinterop.AndroidView
import org.osmdroid.config.Configuration
import org.osmdroid.events.MapEventsReceiver
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.MapView
import org.osmdroid.views.overlay.MapEventsOverlay
import org.osmdroid.views.overlay.Marker


@Composable
fun OSMMapWithMarkers(
    context: Context,
    happyPlaces: List<HappyPlace>,
    onMapTap: ((GeoPoint) -> Unit)? = null, // Callback für Tap-Events
    selectedPoint: GeoPoint? = null,
) {
    Configuration.getInstance().load(context, context.getSharedPreferences("osmdroid", 0))

    AndroidView(
        factory = { ctx ->
            val mapView = MapView(ctx)
            mapView.layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
            mapView.setMultiTouchControls(true)
            mapView.controller.setZoom(15.0)
            if (happyPlaces.isNotEmpty()) {
                mapView.controller.setCenter(GeoPoint(happyPlaces[0].latitude, happyPlaces[0].longitude))
            }

            // Marker für gespeicherte Orte
            happyPlaces.forEach { place ->
                val marker = Marker(mapView)
                marker.position = GeoPoint(place.latitude, place.longitude)
                marker.title = place.title
                marker.subDescription = place.description
                marker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM)
                mapView.overlays.add(marker)
            }

            // Overlay für Tap-Events
            val eventReceiver = object : MapEventsReceiver {
                override fun singleTapConfirmedHelper(p: GeoPoint?): Boolean {
                    p?.let { onMapTap?.invoke(it) }
                    return true
                }
                override fun longPressHelper(p: GeoPoint?): Boolean {
                    return false
                }
            }
            val mapEventsOverlay = MapEventsOverlay(eventReceiver)
            mapView.overlays.add(mapEventsOverlay)

            // Marker für ausgewählten Punkt (falls gesetzt)
            selectedPoint?.let {
                val marker = Marker(mapView)
                marker.position = it
                marker.title = "Selected point"
                marker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM)
                mapView.overlays.add(marker)
            }

            mapView
        },
        update = { mapView ->
            // Alle Marker neu setzen
            mapView.overlays.clear()
            happyPlaces.forEach { place ->
                val marker = Marker(mapView)
                marker.position = GeoPoint(place.latitude, place.longitude)
                marker.title = place.title
                marker.subDescription = place.description
                marker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM)
                mapView.overlays.add(marker)
            }
            // Tap-Overlay wieder hinzufügen
            val eventReceiver = object : MapEventsReceiver {
                override fun singleTapConfirmedHelper(p: GeoPoint?): Boolean {
                    p?.let { onMapTap?.invoke(it) }
                    return true
                }
                override fun longPressHelper(p: GeoPoint?): Boolean {
                    return false
                }
            }
            val mapEventsOverlay = MapEventsOverlay(eventReceiver)
            mapView.overlays.add(mapEventsOverlay)

            // Marker für ausgewählten Punkt
            selectedPoint?.let {
                val marker = Marker(mapView)
                marker.position = it
                marker.title = "Selected point"
                marker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM)
                mapView.overlays.add(marker)
            }
            if (happyPlaces.isNotEmpty()) {
                mapView.controller.setCenter(GeoPoint(happyPlaces[0].latitude, happyPlaces[0].longitude))
            }
        }
    )
}