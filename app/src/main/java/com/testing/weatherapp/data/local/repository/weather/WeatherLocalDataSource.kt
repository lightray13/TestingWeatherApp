package com.testing.weatherapp.data.local.repository.weather

import androidx.lifecycle.LiveData
import com.testing.weatherapp.data.local.database.WeatherDatabase
import com.testing.weatherapp.data.local.database.model.WeatherEntity
import javax.inject.Inject

class WeatherLocalDataSource @Inject constructor(private val database: WeatherDatabase) {

    fun weatherEntityByLatAndLot(lat: Double, lon: Double): LiveData<WeatherEntity?> = database.weatherDao().weatherEntityLiveDataByLatAndLot(lat, lon)

    suspend fun addWeatherEntityIntoDatabase(weatherEntity: WeatherEntity) {
        database.weatherDao().insertWeatherEntity(weatherEntity)
    }
}