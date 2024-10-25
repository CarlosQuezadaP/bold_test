package com.bold.main_weather.data.api

import com.bold.main_weather.data.api.dto.CitySearchResponseDto
import com.bold.main_weather.data.api.dto.nextDays.CityNextDaysResponseDto
import com.bold.network.ApiResponse

interface IWeatherApi {
    suspend fun todayWeather(cityName: String): ApiResponse<List<CitySearchResponseDto>>
    suspend fun nextDaysWeather( urlCity: String): ApiResponse<CityNextDaysResponseDto>
}
