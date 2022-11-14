package com.testing.weatherapp.ui.splash

import androidx.lifecycle.ViewModel
import com.testing.weatherapp.data.local.repository.splash.SplashRepository
import com.testing.weatherapp.util.Constants
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(private val repository: SplashRepository): ViewModel() {

    fun setCoordinates(lat: String = Constants.DEFAULT_CITY_LAT, lon: String = Constants.DEFAULT_CITY_LON) {
        repository.setCoordinates(lat, lon)
    }
}