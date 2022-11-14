package com.testing.weatherapp.data.local.repository.weather

import javax.inject.Inject
import com.testing.weatherapp.api.Result
import com.testing.weatherapp.api.successed
import com.testing.weatherapp.data.local.database.model.WeatherEntity
import com.testing.weatherapp.data.local.preferences.SharedPreferenceStorage
import com.testing.weatherapp.util.Constants

class WeatherRepository @Inject constructor(
    private val weatherLocalDataSource: WeatherLocalDataSource,
    private val weatherRemoteDataSource: WeatherRemoteDataSource,
    private val preferenceStorage: SharedPreferenceStorage
) {

    val mainWeather = weatherLocalDataSource.weatherEntityByLatAndLot(getLatitude().toDouble(), getLongitude().toDouble())

    suspend fun loadWeatherByLatAndLot() {
        when(val result = weatherRemoteDataSource.weatherEntityByLatAndLot(getLatitude(), getLongitude(), Constants.DEFAULT_LANGUAGE)) {
            is Result.Success -> {
                if (result.successed) {
                    val weatherResult = result.data
                    weatherResult.let {
                        val weatherEntity = WeatherEntity(
                            it.geoObject?.locality?.name ?: Constants.DEFAULT_CITY,
                            it.forecasts?.get(0)?.date ?: "",
                            it.forecasts?.get(0)?.dateTs,
                            it.info?.lat,
                            it.info?.lon,
                            it.fact?.temp,
                            it.fact?.feelsLike,
                            it.fact?.icon,
                            it.fact?.condition,
                            it.fact?.windSpeed,
                            it.fact?.windDir,
                            it.fact?.pressureMm,
                            it.fact?.humidity,
                            it.forecasts
                        )
                        weatherLocalDataSource.addWeatherEntityIntoDatabase(weatherEntity)
                    }
                    Result.Success(true)
                } else {
                    Result.Error(Constants.GENERIC_ERROR)
                }
            }
            else -> result as Result.Error
        }
    }

    private fun getLatitude(): String {
        return preferenceStorage.lat
    }

    private fun getLongitude(): String {
        return preferenceStorage.lon
    }
}