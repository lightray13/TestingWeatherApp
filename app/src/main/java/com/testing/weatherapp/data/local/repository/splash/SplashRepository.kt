package com.testing.weatherapp.data.local.repository.splash

import com.testing.weatherapp.data.local.preferences.SharedPreferenceStorage
import javax.inject.Inject

class SplashRepository @Inject constructor(
    private val preferenceStorage: SharedPreferenceStorage
) {

    fun setCoordinates(lat: String, lon: String) {
        preferenceStorage.lat = lat
        preferenceStorage.lon = lon
    }
}