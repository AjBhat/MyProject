package com.example.weatherapp.model

data class ForecastList (
    val dt: Int,
    val dt_txt: String,
    val main: Main,
    val pop: Int,
)