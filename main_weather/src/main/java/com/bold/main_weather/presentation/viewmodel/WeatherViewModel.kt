package com.bold.main_weather.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asFlow
import androidx.lifecycle.viewModelScope
import com.bold.main_weather.data.repo.IWeatherRepo
import com.bold.main_weather.data.repo.models.CitySearchModel
import com.bold.network.ApiResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class WeatherViewModel(private val iWeatherRepo: IWeatherRepo) : ViewModel() {

    val editTextContent = MutableLiveData<String>()

    init {
        viewModelScope.launch {
            editTextContent.asFlow()
                .collect { newText ->
                    if (newText.length < 3) {
                        _weatherState.value = ApiResponse.Success(emptyList())
                    } else {
                        getWeather(newText)
                    }
                }
        }
    }

    private val _weatherState = MutableStateFlow<ApiResponse<List<CitySearchModel>>>(ApiResponse.Loading)

    val weatherState: StateFlow<ApiResponse<List<CitySearchModel>>> = _weatherState.asStateFlow()

    private fun getWeather(cityName: String) {
        viewModelScope.launch {
            _weatherState.value = ApiResponse.Loading
            _weatherState.value = iWeatherRepo.todayWeather(cityName)
        }
    }

    override fun onCleared() {
        super.onCleared()
        editTextContent.removeObserver {  }
    }
}