package com.example.weatherapp.di.module


import com.example.weatherapp.model.remote.APIInterface
import com.example.weatherapp.utils.Constant
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object Netmodule {

    @Provides
    fun getBASE_URL()= Constant.BASE_URL

    @Provides
    @Singleton
    fun provideRetrofit(BASE_URL:String): APIInterface =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(APIInterface::class.java)


}