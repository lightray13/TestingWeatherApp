package com.testing.weatherapp.data.local.repository.cityList

import com.testing.weatherapp.api.ApiInterface
import com.testing.weatherapp.api.BaseRemoteDataSource
import javax.inject.Inject
import com.testing.weatherapp.api.Result
import com.testing.weatherapp.api.model.Coordinates

class CityListRemoteDataSource @Inject constructor(private val service: ApiInterface): BaseRemoteDataSource() {

    suspend fun cityCoordinates(url: String, apikey: String, geocode: String, format: String): Result<Coordinates> =
        getResult {
            service.getCityCoordinates(url, apikey, geocode, format)
        }
}