package com.example.weatherapplication

import android.app.Application
import androidx.lifecycle.*
import com.example.weatherapplication.date.WeatherApiResult
import kotlinx.coroutines.launch
import java.lang.IllegalArgumentException
import javax.inject.Inject

class WeatherInfoViewModel (application: Application):AndroidViewModel(application) {

    private val repository = WeatherInfoRepository.instance
    //default:久留米
    private var city: String = "400040"
    lateinit var weatherList: LiveData<WeatherApiResult>


    init {
        loadWeather(city)
    }

    private fun loadWeather (city:String){
        viewModelScope.launch {
            weatherList = repository.getWeather(city)
        }
    }
}