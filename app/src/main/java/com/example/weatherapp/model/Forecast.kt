package com.example.weatherapp.model

data class Forecast(
    val city: City,
    val cod: String,
    val list: List<ForecastList>,
    val message: Int
)