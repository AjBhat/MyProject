package com.example.weatherapp.di

import android.app.Application
import cat.ereza.customactivityoncrash.config.CaocConfig
import com.example.weatherapp.utils.ErrorActivity
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MyApplication:Application() {
    override fun onCreate() {
        super.onCreate()
        CaocConfig.Builder.create()
            .trackActivities(true)
            .errorActivity(ErrorActivity::class.java)
            .apply()
    }
}