package com.testing.weatherapp.ui.cityList

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.testing.weatherapp.api.Result
import com.testing.weatherapp.data.local.repository.cityList.CityListRepository
import com.testing.weatherapp.util.Constants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CityListViewModel @Inject constructor(private val repository: CityListRepository): ViewModel() {

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _toastError = MutableLiveData<String>()
    val toastError: LiveData<String> = _toastError

    private val _toastSuccess = MutableLiveData<String?>()
    val toastSuccess: LiveData<String?> = _toastSuccess

    val cityListData = repository.cityList

    fun insertCityEntityToList(geocode: String) {
        viewModelScope.launch(Dispatchers.IO) {
            _isLoading.postValue(true)
            repository.loadCityCoordinates(geocode)
            _isLoading.postValue(false)
        }
    }

    fun deleteCityEntityFromList(name: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val result = repository.deleteCityEntityFromDatabase(name)
            if (result is Result.Success) _toastSuccess.postValue(result.data)
            else if (result is Result.Error) _toastError.postValue(result.message)
        }
    }

    fun setCoordinates(lat: String = Constants.DEFAULT_CITY_LAT, lon: String = Constants.DEFAULT_CITY_LON) {
        repository.setCoordinates(lat, lon)
    }
}