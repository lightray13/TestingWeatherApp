package com.testing.weatherapp.data.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.testing.weatherapp.data.local.database.converter.Converter
import com.testing.weatherapp.data.local.database.dao.CityDao
import com.testing.weatherapp.data.local.database.dao.WeatherDao
import com.testing.weatherapp.data.local.database.model.CityEntity
import com.testing.weatherapp.data.local.database.model.WeatherEntity

@Database(entities = [WeatherEntity::class, CityEntity::class], version = 1, exportSchema = false)
@TypeConverters(value = [Converter::class])
abstract class WeatherDatabase: RoomDatabase() {
    abstract fun weatherDao(): WeatherDao
    abstract fun cityDao(): CityDao

    companion object{
        fun buildDatabase(context: Context): WeatherDatabase {
            return Room.databaseBuilder(context, WeatherDatabase::class.java, "Weather")
                .build()
        }
    }
}