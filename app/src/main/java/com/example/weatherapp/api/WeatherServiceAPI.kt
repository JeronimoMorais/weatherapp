package com.example.weatherapp.api

import com.example.weatherapp.BuildConfig
import com.example.weatherapp.BuildConfig.WEATHER_API_KEY
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

    @GET("current.json?key=$WEATHER_API_KEY&lang=pt")
    fun weather(@Query("q") query: String): Call<APICurrentWeather?>

    companion object {
        const val BASE_URL = "https://api.weatherapi.com/v1/"
    }

    @GET("forecast.json?key=$WEATHER_API_KEY&days=10&lang=pt")
    fun forecast(@Query("q") name: String): Call<APIWeatherForecast?>
}
