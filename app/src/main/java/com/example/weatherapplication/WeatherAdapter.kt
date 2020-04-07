package com.example.weatherapplication

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class WeatherAdapter (context: Context):RecyclerView.Adapter<WeatherAdapter.WeatherViewHolder>(){

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var weathers = emptyList<Weather>()

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

    override fun getItemCount(): Int = weathers.size

    override fun onBindViewHolder(holder: WeatherViewHolder, position: Int) {
        val current = weathers[position]
        holder.cityTextView.text=current.city
        holder.dateTextView.text=current.date
        holder.telopTextView.text=current.telop
        holder.maxTemperatureTextView.text=current.maxTemperature.toString()
        holder.minTemperatureTextView.text=current.minTemperature.toString()
    }

    internal fun setWeather(weathers: List<Weather>){
        this.weathers = weathers
        notifyDataSetChanged()
    }

}