package com.example.happyplacesapp.home

sealed class Screen(val route: String) {
    object Map : Screen("map")
    object AddPlace : Screen("add_place")
    object PlacesList : Screen("places_list")
    object PlaceDetail : Screen("place_detail/{placeId}") {
        fun createRoute(placeId: Long) = "place_detail/$placeId"
    }
}