package com.example.weatherapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var weatherInfoViewModel: WeatherInfoViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerview)
        val adapter = WeatherAdapter(this)
        recyclerView.adapter=adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        weatherInfoViewModel = ViewModelProvider(this).get(WeatherInfoViewModel::class.java)
        weatherInfoViewModel.weatherList.observe(this, Observer { weatherList ->
            weatherList?.let {
                //アダプタに設定
                adapter.setWeather(it)
                }
        })
    }
}
