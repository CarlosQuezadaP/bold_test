package com.bold.main_weather.presentation

import com.bold.main_weather.data.repo.models.CitySearchModel

interface CityClickListener {
    fun onCityClick(city: CitySearchModel)
}