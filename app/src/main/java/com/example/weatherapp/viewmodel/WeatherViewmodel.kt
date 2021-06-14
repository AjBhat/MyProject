package com.example.weatherapplication.viewmodel


import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.example.weatherapp.model.Forecast
import com.example.weatherapp.model.Weather
import com.example.weatherapp.utils.Resource


import com.example.weatherapplication.repository.WeatherRepository

import kotlinx.coroutines.launch


class WeatherViewmodel
@ViewModelInject constructor(private val weatherRepository: WeatherRepository):ViewModel(){



    private val _respWeather = MutableLiveData<Resource<Weather>>()
    val weatherResp: LiveData<Resource<Weather>>
        get() = _respWeather

    private val _respForecast = MutableLiveData<Resource<Forecast>>()
    val forecastResp:LiveData<Resource<Forecast>>
        get() = _respForecast

    init {
        getWeather()
        getForecast()
    }

    private fun getForecast() = viewModelScope.launch {
        weatherRepository.getForecast().let {
            if (it.isSuccessful){
                _respForecast.postValue(Resource.success(it.body()))
            }else{
                Log.d("TAG","getwether error: ${it.message()}")
            }
        }
    }


    private  fun getWeather() = viewModelScope.launch{
        _respWeather.postValue(Resource.loading(null))
        weatherRepository.getWeather().let { response ->
            if (response.isSuccessful){
                _respWeather.postValue(Resource.success(response.body()))
            }else{
                Log.d("TAG","getwether error: ${response.message()}")
            }
        }

    }


}

