package com.example.weatherapp.model

data class City(
    val name: String,
    val weather: String? = null,
    val location: com.google.android.gms.maps.model.LatLng? = null
)