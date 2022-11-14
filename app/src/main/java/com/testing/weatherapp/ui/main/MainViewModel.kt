package com.testing.weatherapp.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.testing.weatherapp.data.local.repository.weather.WeatherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: WeatherRepository): ViewModel() {

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    val mainWeather = repository.mainWeather

    fun loadWeatherFromApi() {
        viewModelScope.launch(Dispatchers.IO) {
            _isLoading.postValue(true)
            repository.loadWeatherByLatAndLot()
            _isLoading.postValue(false)
        }
    }
}