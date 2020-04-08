package com.example.weatherapplication

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.weatherapplication.date.WeatherApiResult
import dagger.Component
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject
import javax.inject.Singleton


class WeatherInfoRepository{

    private val retrofit = Retrofit.Builder()
        .baseUrl(API_BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val webservice:Webservice =retrofit.create(Webservice::class.java)

    suspend fun getWeather(cityId:String):LiveData<WeatherApiResult>{
        val data = MutableLiveData<WeatherApiResult>()

        //TODO:Retrofit2のresponseとPOJOのmappingを調べる
        webservice.getWeather(cityId).enqueue(object : Callback<WeatherApiResult>{
            override fun onFailure(call: Call<WeatherApiResult>, t: Throwable) {
                t.printStackTrace()
            }

            override fun onResponse(call: Call<WeatherApiResult>, response: Response<WeatherApiResult>) {
                //data.postValuでもよい
                data.value = response.body()
            }
        })
        return data
    }

    //singletonのrepositoryを返すFactory
    companion object Factory {
        val API_BASE_URL = "http://weather.livedoor.com/"
        val instance: WeatherInfoRepository
            get() = WeatherInfoRepository()

    }
}
