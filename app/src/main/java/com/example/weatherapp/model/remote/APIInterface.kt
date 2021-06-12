package com.example.weatherapp.model.remote

import com.example.weatherapp.model.Forecast
import com.example.weatherapp.utils.Constant.APIID
import com.example.weatherapp.utils.Constant.city_name
import com.example.weatherapp.utils.Constant.unit
import com.example.weatherapp.model.Weather
import retrofit2.Response


import retrofit2.http.GET
import retrofit2.http.Query

interface APIInterface {

    @GET("weather")
    suspend fun getWeather(
        @Query("q") city:String=city_name,
        @Query("APPID") appid:String=APIID,
        @Query("units") units:String=unit
    ):Response<Weather>

    @GET("forecast")
    suspend fun getForecast(
        @Query("q") city:String=city_name,
        @Query("APPID") appid:String=APIID,
        @Query("units") units:String=unit
    ):Response<Forecast>
}