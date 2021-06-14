package com.example.weatherapp.utils

import java.text.SimpleDateFormat
import java.util.*


object Constant {
    var BASE_URL:String="https://api.openweathermap.org/data/2.5/"
    var city_name:String="Bengaluru"
    var unit:String="metric"
    var APIID:String="9b8cb8c7f11c077f8c4e217974d9ee40"

    fun dayConverter(time: Long) : String{
        var converter = SimpleDateFormat("EEEE")
        var convertedDay = converter.format(Date(time*1000))

        return convertedDay
    }

}