package com.testing.weatherapp.data.local.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.testing.weatherapp.data.local.database.model.WeatherEntity

@Dao
interface WeatherDao {

    @Query("SELECT * FROM weather_list WHERE lat = :lat AND lon = :lon")
    suspend fun weatherEntityByLatAndLot(lat: Double, lon: Double): WeatherEntity?

    @Query("SELECT * FROM weather_list WHERE lat = :lat AND lon = :lon")
    fun weatherEntityLiveDataByLatAndLot(lat: Double, lon: Double): LiveData<WeatherEntity?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWeatherEntity(data: WeatherEntity): Long
}