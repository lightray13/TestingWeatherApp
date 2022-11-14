package com.testing.weatherapp.data.local.repository.weather

import com.testing.weatherapp.api.ApiInterface
import com.testing.weatherapp.api.BaseRemoteDataSource
import javax.inject.Inject
import com.testing.weatherapp.api.Result
import com.testing.weatherapp.api.model.Weather

class WeatherRemoteDataSource @Inject constructor(private val service: ApiInterface): BaseRemoteDataSource() {

    suspend fun weatherEntityByLatAndLot(lat: String, lon: String, lang: String): Result<Weather> =
        getResult {
            service.weatherList(lat, lon, lang)
        }
}