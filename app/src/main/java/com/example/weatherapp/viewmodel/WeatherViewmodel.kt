package com.example.weatherapplication.viewmodel


import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.example.weatherapp.model.Weather
import com.example.weatherapp.utils.Resource


import com.example.weatherapplication.repository.WeatherRepository

import kotlinx.coroutines.launch
import javax.inject.Inject


class WeatherViewmodel
@ViewModelInject constructor(private val weatherRepository: WeatherRepository):ViewModel(){



    private val _resp = MutableLiveData<Resource<Weather>>()
    val weatherResp: LiveData<Resource<Weather>>
        get() = _resp

    init {
        getWeather()
    }


    private  fun getWeather() = viewModelScope.launch{
        _resp.postValue(Resource.loading(null))
        weatherRepository.getWeather().let { response ->
            if (response.isSuccessful){
                _resp.postValue(Resource.success(response.body()))
            }else{
                Log.d("TAG","getwether error: ${response.message()}")
            }
        }

    }


}

