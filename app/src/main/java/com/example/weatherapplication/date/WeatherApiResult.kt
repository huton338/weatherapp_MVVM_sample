package com.example.weatherapplication.date

import com.example.weatherapplication.date.*

data class WeatherApiResult(
    val copyright: Copyright,
    val description: Description,
    val forecasts: List<Forecast>,
    val link: String,
    val location: Location,
    val pinpointLocations: List<PinpointLocation>,
    val publicTime: String,
    val title: String
)