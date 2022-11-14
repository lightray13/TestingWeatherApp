package com.testing.weatherapp.data.local.database.converter

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.testing.weatherapp.api.model.Forecast
import java.lang.reflect.Type

class Converter {
    companion object {

        @TypeConverter
        @JvmStatic
        fun forecastListToString(list: List<Forecast>): String? {
            val gson = Gson()
            return gson.toJson(list)
        }

        @TypeConverter
        @JvmStatic
        fun stringToForecastList(data: String): List<Forecast> {
            val listType: Type = object: TypeToken<List<Forecast?>?>(){}.type
            return Gson().fromJson(data, listType)
        }
    }
}