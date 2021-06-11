package com.example.weatherapplication.repository


import com.example.weatherapp.model.remote.APIInterface
import javax.inject.Inject

class WeatherRepository
@Inject constructor(private val apiInterface: APIInterface){

    suspend fun getWeather() = apiInterface.getWeather()
}