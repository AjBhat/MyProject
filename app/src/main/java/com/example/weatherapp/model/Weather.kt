package com.example.weatherapp.model

import com.example.weatherapp.model.Main

data class Weather(
    val base: String,
    val cod: Int,
    val dt: Int,
    val id: Int,
    val main: Main,
    val name: String,
    val timezone: Int,
    val visibility: Int,

    )