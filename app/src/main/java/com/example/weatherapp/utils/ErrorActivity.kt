package com.example.weatherapp.utils

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.ActionBar
import cat.ereza.customactivityoncrash.CustomActivityOnCrash
import com.example.weatherapp.R
import kotlinx.android.synthetic.main.activity_error.*

class ErrorActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_error)
        val actionBar: ActionBar? = supportActionBar
        actionBar?.hide()
        val config = CustomActivityOnCrash.getConfigFromIntent(intent)

        /**
         * If config is null or not getting an intent simply finish the app
         */
        if (config == null) {
            finish()
            return
        }

        if (config.isShowRestartButton && config.restartActivityClass != null) {
            button.setOnClickListener {
                CustomActivityOnCrash.restartApplication(
                    this,
                    config
                )
            }
        } else {
            button.setOnClickListener {
                CustomActivityOnCrash.closeApplication(
                    this,
                    config
                )
            }
        }
    }
}