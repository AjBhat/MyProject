package com.example.weatherapplication.viewmodel


import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.example.weatherapp.model.Weather


import com.example.weatherapplication.repository.WeatherRepository

import kotlinx.coroutines.launch





class WeatherViewmodel
@ViewModelInject constructor(private val weatherRepository: WeatherRepository):ViewModel(){



    private val _resp = MutableLiveData<Weather>()
    val weatherResp: LiveData<Weather>
        get() = _resp

    init {
        getWeather()
    }


    private  fun getWeather() = viewModelScope.launch{
        weatherRepository.getWeather().let { response ->
            if (response.isSuccessful){
                _resp.postValue(response.body())
            }else{
                Log.d("TAG","getwether error: ${response.message()}")
            }
        }

    }


}

