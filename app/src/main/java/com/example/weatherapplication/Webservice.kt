package com.example.weatherapplication

import com.example.weatherapplication.date.WeatherApiResult
import dagger.Component
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface Webservice {

    @GET("forecast/webservice/json/v1")
    fun getWeather(@Query("city")city:String): Call<WeatherApiResult>
}