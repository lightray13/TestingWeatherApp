package com.testing.weatherapp.data.local.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.testing.weatherapp.api.model.Forecast
import com.testing.weatherapp.util.Constants

@Entity(tableName = "weather_list")
data class WeatherEntity(
    @PrimaryKey val name: String = Constants.DEFAULT_CITY,
    val date: String?,
    val dateTs: Long?,
    val lat: Double?,
    val lon: Double?,
    val temp: Int?,
    val feelsLike: Int?,
    val icon: String?,
    val condition: String?,
    val windSpeed: Double?,
    val windDir: String?,
    val pressureMm: Int?,
    val humidity: Int?,
    val forecasts: List<Forecast>?
)