package com.example.weatherapp.utils


import android.content.Context

import android.view.LayoutInflater
import android.view.View

import androidx.appcompat.app.AppCompatDialog
import com.example.weatherapp.R


class ProgressDialog(context: Context): AppCompatDialog(context, R.style.NewDialog) {

    init {
        val systemService = getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        var view: View = systemService.inflate(R.layout.dialog_layout, null)
        setContentView(view)
        setCancelable(false)
        setCanceledOnTouchOutside(false)
    }


}