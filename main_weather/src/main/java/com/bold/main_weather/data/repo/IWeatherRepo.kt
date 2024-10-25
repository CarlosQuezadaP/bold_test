package com.bold.main_weather.data.repo

import com.bold.main_weather.data.api.CityNextDaysModel
import com.bold.main_weather.data.repo.models.CitySearchModel
import com.bold.network.ApiResponse

interface IWeatherRepo {
    suspend fun todayWeather(cityName: String): ApiResponse<List<CitySearchModel>>
    suspend fun nextDaysWeather(urlCity: String): ApiResponse<CityNextDaysModel>
}