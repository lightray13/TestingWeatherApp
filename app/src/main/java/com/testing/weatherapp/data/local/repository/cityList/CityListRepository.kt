package com.testing.weatherapp.data.local.repository.cityList

import androidx.lifecycle.LiveData
import com.testing.weatherapp.util.Constants
import javax.inject.Inject
import com.testing.weatherapp.api.Result
import com.testing.weatherapp.api.successed
import com.testing.weatherapp.data.local.database.model.CityEntity
import com.testing.weatherapp.data.local.preferences.SharedPreferenceStorage

class CityListRepository @Inject constructor(
    private val cityListLocalDataSource: CityListLocalDataSource,
    private val cityListRemoteDataSource: CityListRemoteDataSource,
    private val preferenceStorage: SharedPreferenceStorage
) {
    val cityList: LiveData<List<CityEntity>> = cityListLocalDataSource.cityList

    suspend fun loadCityCoordinates(geocode: String) {
        when(val result = cityListRemoteDataSource.cityCoordinates(Constants.GEOCODE_MAPS_URL,
        Constants.GEOCODE_MAPS_KEY, geocode, Constants.GEOCODE_MAPS_FORMAT)) {
            is Result.Success -> {
                if (result.successed) {
                    val featureMember = result.data.response?.geoObjectCollection?.featureMember
                    if (!featureMember.isNullOrEmpty()) {
                        val resultCoordinates =
                            featureMember.get(0).geoObject?.point?.pos
                        val resultCityText =
                            featureMember.get(0).geoObject?.metaDataProperty?.geocoderMetaData?.text
                        resultCoordinates?.let {
                            val coordinates = resultCoordinates.split(" ").toTypedArray()
                            val cityEntity = CityEntity(
                                resultCityText ?: Constants.DEFAULT_CITY,
                                coordinates[1],
                                coordinates[0]
                            )
                            cityListLocalDataSource.addCityEntityIntoDatabase(cityEntity)
                        }
                    }
                    Result.Success(true)
                } else {
                    Result.Error(Constants.GENERIC_ERROR)
                }
            }
            else -> result as Result.Error
        }
    }

    suspend fun deleteCityEntityFromDatabase(name: String): Result<String> {
        val result = cityListLocalDataSource.deleteCityEntityFromDatabase(name)
        return if (result) Result.Success("Местоположение удалено из списка")
        else Result.Error("Ошибка при удалении местоположения")
    }

    fun setCoordinates(lat: String, lon: String) {
        preferenceStorage.lat = lat
        preferenceStorage.lon = lon
    }
}