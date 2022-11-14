package com.testing.weatherapp.data.local.repository.cityList

import androidx.lifecycle.LiveData
import com.testing.weatherapp.data.local.database.WeatherDatabase
import com.testing.weatherapp.data.local.database.model.CityEntity
import javax.inject.Inject

class CityListLocalDataSource @Inject constructor(private val database: WeatherDatabase) {
    val cityList: LiveData<List<CityEntity>> = database.cityDao().cityList()

    suspend fun addCityEntityIntoDatabase(cityEntity: CityEntity) {
        database.cityDao().insertCityEntity(cityEntity)
    }

    suspend fun deleteCityEntityFromDatabase(name: String): Boolean {
        val cityEntity = database.cityDao().cityEntityByName(name)
        cityEntity?.let {
            if (database.cityDao().deleteCityEntity(cityEntity) > 0) {
                return true
            }
        }
        return false
    }
}