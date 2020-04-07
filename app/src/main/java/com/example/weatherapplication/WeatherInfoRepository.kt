package com.example.weatherapplication

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
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

    suspend fun getWeather(cityId:String):LiveData<List<Weather>>{
        val data = MutableLiveData<List<Weather>>()

        //TODO:Retrofit2のresponseとPOJOのmappingを調べる
        webservice.getWeather(cityId).enqueue(object : Callback<List<Weather>>{
            override fun onFailure(call: Call<List<Weather>>, t: Throwable) {
                t.printStackTrace()
            }

            override fun onResponse(call: Call<List<Weather>>, response: Response<List<Weather>>) {
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
