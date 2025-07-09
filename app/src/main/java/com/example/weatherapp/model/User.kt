package com.example.weatherapp.model

import androidx.compose.runtime.mutableStateOf

data class User(val name: String, val email: String) {
    private val _user = mutableStateOf<User?> (null)
    val user : User? get() = _user.value
}