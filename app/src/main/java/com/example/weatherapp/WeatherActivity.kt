package com.example.weatherapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.example.weatherapp.databinding.ActivityMainBinding
import com.example.weatherapp.utils.ProgressDialog
import com.example.weatherapp.utils.Status
import com.example.weatherapplication.viewmodel.WeatherViewmodel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*

@AndroidEntryPoint
class WeatherActivity : AppCompatActivity() {

    private lateinit var binding:ActivityMainBinding
    private  val viewModel:WeatherViewmodel by viewModels()
    private var progressDialog:ProgressDialog?= null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)
        setupObserver()
    }

    private fun setupObserver() {
        viewModel.weatherResp.observe(this, Observer {
            when(it.status){
                Status.SUCCESS ->{
                    it.data?.let {
                        textCity.text=it.name
                        textTemp.text=it.main.temp.toString()+"°"
                    }
                }
                Status.LOADING ->{
                    progressDialog= ProgressDialog(this)
                    progressDialog?.show()
                }
                Status.ERROR ->{
                    
                }
            }
        })

    }
}