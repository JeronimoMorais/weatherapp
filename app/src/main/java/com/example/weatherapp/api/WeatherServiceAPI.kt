package com.example.weatherapp.api

import com.example.weatherapp.BuildConfig
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import com.example.weatherapp.api.APILocation

interface WeatherServiceAPI {

    @GET("search.json?lang=pt_br")
    fun search(
        @Query("key") apiKey: String = BuildConfig.WEATHER_API_KEY,
        @Query("q") query: String
    ): Call<List<APILocation>>

    companion object {
        const val BASE_URL = "https://api.weatherapi.com/v1/"
    }
}
