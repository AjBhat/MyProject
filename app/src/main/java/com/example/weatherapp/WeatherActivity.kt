package com.example.weatherapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import androidx.activity.viewModels
import com.example.weatherapp.databinding.ActivityMainBinding
import com.example.weatherapplication.viewmodel.WeatherViewmodel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*

@AndroidEntryPoint
class WeatherActivity : AppCompatActivity() {

    private lateinit var binding:ActivityMainBinding
    private  val viewModel:WeatherViewmodel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)

        viewModel.weatherResp.observe(this,{
            textCity.text=it.name
            textTemp.text=it.main.temp.toString()+"°"
        })
    }
}