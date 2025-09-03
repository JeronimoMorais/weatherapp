package com.example.weatherapp.api

import android.graphics.Bitmap
import com.google.android.gms.maps.model.LatLng
import com.squareup.picasso.Picasso
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class WeatherService {
    private var weatherAPI: WeatherServiceAPI
    init {
        val retrofitAPI = Retrofit.Builder()
            .baseUrl(WeatherServiceAPI.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).build()
        weatherAPI = retrofitAPI.create(WeatherServiceAPI::class.java)
    }
    suspend fun getName(lat: Double, lng: Double):String? = withContext(Dispatchers.IO){
        search("$lat,$lng")?.name // retorno
    }
    suspend fun getLocation(name: String): LatLng? = withContext(Dispatchers.IO) {
        val location = search(name)
        if (location != null) LatLng(location.lat!!, location.lon!!) else null
    }

    //LEMBRAR DE VERIFICAR A QUESTÃO DOS 02 PARÂMETROS QUE FORAM PASSADOS
    private fun search(query: String) : APILocation? {
        val call: Call<List<APILocation>> = weatherAPI.search(query = query)
        val apiLoc = call.execute().body()
        return if (!apiLoc.isNullOrEmpty()) apiLoc[0] else null
    }
    suspend fun getWeather(name: String): APICurrentWeather? =
        withContext(Dispatchers.IO) {
            val call: Call<APICurrentWeather?> = weatherAPI.weather(name)
            call.execute().body() // retorno
        }
    suspend fun getForecast(name: String) : APIWeatherForecast? = withContext(Dispatchers.IO) {
        val call: Call<APIWeatherForecast?> = weatherAPI.forecast(name)
        call.execute().body()
    }
    suspend fun getBitmap(imgUrl: String) : Bitmap? = withContext(Dispatchers.IO) {
        Picasso.get().load(imgUrl).get() // retorno
    }
}