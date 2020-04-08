package com.example.weatherapplication

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapplication.date.WeatherApiResult

class WeatherAdapter (context: Context):RecyclerView.Adapter<WeatherAdapter.WeatherViewHolder>(){

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var weathers:WeatherApiResult? = null

    inner class WeatherViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        val cityTextView: TextView = itemView.findViewById(R.id.cityText)
        val dateTextView: TextView = itemView.findViewById(R.id.dateText)
        val telopTextView: TextView = itemView.findViewById(R.id.telopText)
        val maxTemperatureTextView: TextView = itemView.findViewById(R.id.maxTemperatureText)
        val minTemperatureTextView: TextView = itemView.findViewById(R.id.minTemperatureText)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherViewHolder {
        val itemView = inflater.inflate(R.layout.recyclerview_item,parent,false)
        return(WeatherViewHolder(itemView))
    }

    override fun getItemCount(): Int = weathers!!.forecasts.size

    override fun onBindViewHolder(holder: WeatherViewHolder, position: Int) {
        val current = weathers!!.forecasts[position]
        holder.cityTextView.text=weathers!!.location.city.toString()
        holder.dateTextView.text=current.date
        holder.telopTextView.text=current.telop
        if (current.temperature.max != null ) {
            holder.maxTemperatureTextView.text = current.temperature.max["celsius"]
        }else{
            holder.maxTemperatureTextView.text = "不明"
        }
        if(current.temperature.min != null) {
            holder.minTemperatureTextView.text = current.temperature.min["celsius"]
        }else{
            holder.minTemperatureTextView.text = "不明"
        }
    }

    internal fun setWeather(weatherApiResult: WeatherApiResult){
        this.weathers = weatherApiResult
        notifyDataSetChanged()
    }

}