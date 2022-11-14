package com.testing.weatherapp.api.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.testing.weatherapp.R
import com.testing.weatherapp.api.adapters.model.TimeWeather
import com.testing.weatherapp.util.ImageLoader
import kotlinx.android.synthetic.main.wether_hour_item.view.*

class TimeWeatherAdapter() : RecyclerView.Adapter<TimeWeatherAdapter.TimeWeatherViewHolder>(){
    private val timeWeatherList = mutableListOf<TimeWeather>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TimeWeatherViewHolder {
        return TimeWeatherViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.wether_hour_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: TimeWeatherViewHolder, position: Int) {
        holder.bind(timeWeatherList[position])
    }

    override fun getItemCount(): Int {
        return timeWeatherList.size
    }

    fun updateList(list: List<TimeWeather>) {
        this.timeWeatherList.clear()
        this.timeWeatherList.addAll(list)
        notifyDataSetChanged()
    }

    class TimeWeatherViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        fun bind(model: TimeWeather) {
            itemView.weatherTimeTextView.text = model.time
            ImageLoader.loadImage(itemView.weatherHourImageView, model.icon)
            itemView.weatherTempHourTextView.text = model.temp
        }
    }
}