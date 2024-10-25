package com.bold.main_weather.data.repo

import com.bold.main_weather.data.api.CityNextDaysModel
import com.bold.main_weather.data.api.IWeatherApi
import com.bold.main_weather.data.map.mapToModel
import com.bold.main_weather.data.map.mapToModelNext
import com.bold.main_weather.data.repo.models.CitySearchModel
import com.bold.network.ApiResponse


class WeatherRepo(private val iWeatherApi: IWeatherApi) : IWeatherRepo {

    override suspend fun todayWeather(cityName: String): ApiResponse<List<CitySearchModel>> {
        val response = iWeatherApi.todayWeather(cityName)
        val responseModel: ApiResponse<List<CitySearchModel>> = response.mapToModel()
        return responseModel
    }

    override suspend fun nextDaysWeather(urlCity: String): ApiResponse<CityNextDaysModel> {
        val response = iWeatherApi.nextDaysWeather(urlCity)
        val responseModel: ApiResponse<CityNextDaysModel> = response.mapToModelNext()
        return responseModel
    }
}