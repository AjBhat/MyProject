package com.example.weatherapp.view

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import com.example.weatherapp.R
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.activity.viewModels
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.weatherapp.databinding.ActivityMainBinding
import com.example.weatherapp.model.ForecastList
import com.example.weatherapp.utils.Constant
import com.example.weatherapp.utils.ErrorActivity
import com.example.weatherapp.utils.ProgressDialog
import com.example.weatherapp.utils.Status
import com.example.weatherapplication.viewmodel.WeatherViewmodel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.math.roundToLong


@AndroidEntryPoint
class WeatherActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: WeatherViewmodel by viewModels()
    private var progressDialog: ProgressDialog? = null
    var fiveDaysForeCast: MutableList<ForecastList> = mutableListOf<ForecastList>()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val actionBar: ActionBar? = supportActionBar
        actionBar?.hide()
        binding = ActivityMainBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)
        linearForecast.visibility=View.INVISIBLE
        progressDialog = ProgressDialog(this)
        setupObserver()
    }


    private fun setupObserver() {
        viewModel.weatherResp.observe(this, Observer {
            when (it.status) {
                Status.SUCCESS -> {
                    progressDialog?.dismiss()
                    it.data?.let {
                        textCity.text = it.name
                        textTemp.text = it.main.temp.toString() + "°"
                    }
                }
                Status.LOADING -> {
                    progressDialog?.show()
                }
                Status.ERROR -> {
                    progressDialog?.show()
                    var intent = Intent(applicationContext,ErrorActivity::class.java)
                    startActivity(intent)
                    progressDialog?.dismiss()
                }
            }
        })

        viewModel.forecastResp.observe(this, Observer {
            when (it.status) {
                Status.SUCCESS -> {
                    progressDialog?.dismiss()
                    onAnim()
                    it.data?.let {forecast ->
                        for (i in forecast.list.indices step  8){
                            fiveDaysForeCast.add(forecast.list[i])
                        }
                        text_day1.text=Constant.dayConverter(fiveDaysForeCast[1].dt.toLong())
                        text_day2.text=Constant.dayConverter(fiveDaysForeCast[2].dt.toLong())
                        text_day3.text=Constant.dayConverter(fiveDaysForeCast[3].dt.toLong())
                        text_day4.text=Constant.dayConverter(fiveDaysForeCast[4].dt.toLong())
                        text_temp1.text=fiveDaysForeCast[1].main.temp.roundToLong().toString()+" C"
                        text_temp2.text=fiveDaysForeCast[2].main.temp.roundToLong().toString()+" C"
                        text_temp3.text=fiveDaysForeCast[3].main.temp.roundToLong().toString()+" C"
                        text_temp4.text=fiveDaysForeCast[4].main.temp.roundToLong().toString()+" C"
                    }
                }
                Status.ERROR -> {
                    progressDialog?.show()
                    var intent = Intent(applicationContext,ErrorActivity::class.java)
                    startActivity(intent)
                    progressDialog?.dismiss()
                }
            }
        })

    }

    fun onAnim(){
        val animation:Animation =AnimationUtils.loadAnimation(this,R.anim.slide_up)
        linearForecast.visibility=View.VISIBLE
        linearForecast.startAnimation(animation)
    }

}