package com.example.weatherapplication.date

data class Forecast(
    val date: String,
    val dateLabel: String,
    val image: ImageX,
    val telop: String,
    val temperature: Temperature
)