package com.example.weatherapp.model

data class City(
    val name: String,
    var weather: Weather? = null,
    val location: com.google.android.gms.maps.model.LatLng? = null,
    var forecast: List<Forecast>? = null,
    val isMonitored: Boolean = false,
    val salt: Int = 0
)