package com.bold.main_weather.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import com.bold.main_weather.data.api.CityNextDaysModel
import com.bold.main_weather.data.repo.IWeatherRepo
import com.bold.main_weather.data.repo.models.CitySearchModel
import com.bold.network.ApiResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HomeViewModel(private val iWeatherRepo: IWeatherRepo) : ViewModel() {

    val selectedCity = MutableLiveData<CitySearchModel?>(null)

    val nextDayData = MutableLiveData<CityNextDaysModel?>(null)

    val cityName: LiveData<String?> = selectedCity.map { city ->
        city?.let {
            city.name.plus(", ${city.country}")
        } ?: "Unknown"
    }


    val imageUrl: LiveData<String?> = nextDayData.map { image ->
        image?.current?.condition?.icon
    }

    val weatherName: LiveData<String?> = nextDayData.map { image ->
        image?.current?.condition?.text ?: "Unknown"
    }

    val grades: LiveData<String?> = nextDayData.map { nextDay ->
        nextDay?.let {
            nextDay.current.tempC.toInt().toString()
        } ?: "0"
    }

    val date: LiveData<String?> = nextDayData.map { nextDay ->
        nextDay?.current?.lastUpdated ?: "Unknown"
    }

    val wind: LiveData<String?> = nextDayData.map { nextDay ->
        nextDay?.let {
            nextDay.current.windKph.toInt().toString() + " km/h"
        } ?: "Unknown"
    }

    val hum: LiveData<String?> = nextDayData.map { nextDay ->
        nextDay?.let {
            it.current.humidity.toString() + "%"
        } ?: "Unknown"
    }


    fun updateSelectedCity(city: CitySearchModel) {
        selectedCity.value = city
        getAllDays(city)
    }

    private fun getAllDays(city: CitySearchModel) {
        viewModelScope.launch {
            _daysWeather.value = ApiResponse.Loading

            when (val state = iWeatherRepo.nextDaysWeather(city.url)) {
                is ApiResponse.Success -> {
                    nextDayData.value = state.data
                    _daysWeather.value = state
                }

                is ApiResponse.Error -> {

                }

                is ApiResponse.Loading -> {

                }
            }
        }
    }

    private val _daysWeather = MutableStateFlow<ApiResponse<CityNextDaysModel>>(ApiResponse.Loading)
    val daysWeather: StateFlow<ApiResponse<CityNextDaysModel>> = _daysWeather.asStateFlow()
}
