package com.example.weatherapp.model

import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel
import androidx.compose.runtime.mutableStateOf

class MainViewModel : ViewModel() {
    private val _cities = getCities().toMutableStateList()
    private val _user = mutableStateOf<User?>(null)
    val cities get() = _cities.toList()
    fun remove(city: City) {
        _cities.remove(city)
    }

    val user
        get() = _user.value

    fun add(name: String) {
        _cities.add(City(name = name))
    }
}

private fun getCities() = List(20) { i ->
    City(name = "Cidade $i", weather = "Carregando clima...")
}