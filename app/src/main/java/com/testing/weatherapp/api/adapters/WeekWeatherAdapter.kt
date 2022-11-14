package com.testing.weatherapp.api.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.testing.weatherapp.R
import com.testing.weatherapp.api.adapters.model.TimeWeather
import com.testing.weatherapp.util.ImageLoader
import kotlinx.android.synthetic.main.weather_week_item.view.*

class WeekWeatherAdapter() : RecyclerView.Adapter<WeekWeatherAdapter.WeekWeatherViewHolder>(){
    private val timeWeatherList = mutableListOf<TimeWeather>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeekWeatherViewHolder {
        return WeekWeatherViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.weather_week_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: WeekWeatherViewHolder, position: Int) {
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

    class WeekWeatherViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        fun bind(model: TimeWeather) {
            itemView.weatherDateWeekTextView.text = model.time
            ImageLoader.loadImage(itemView.weatherIconWeekImageView, model.icon)
            itemView.weatherTempWeekTextView.text = model.temp
        }
    }
}