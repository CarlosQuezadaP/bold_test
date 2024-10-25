package com.bold.main_weather.data.api

import com.bold.main_weather.data.api.dto.CitySearchResponseDto
import com.bold.main_weather.data.api.dto.nextDays.CityNextDaysResponseDto
import com.bold.network.ApiResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.http.path

class WeatherApi(
    private val httpClient: HttpClient
) : IWeatherApi {

    override suspend fun todayWeather(cityName: String): ApiResponse<List<CitySearchResponseDto>> {
        return try {
            val response = httpClient.get {
                url {
                    path("search.json")
                    parameters.append("q", cityName)
                }
            }.body<List<CitySearchResponseDto>>()
            ApiResponse.Success(response)
        } catch (e: Exception) {
            ApiResponse.Error(e)
        }
    }

    override suspend fun nextDaysWeather(urlCity: String): ApiResponse<CityNextDaysResponseDto> {
        return try {
            val response = httpClient.get {
                url {
                    path("forecast.json")
                    parameters.append("q", urlCity)
                    parameters.append("days", 3.toString())
                }
            }.body<CityNextDaysResponseDto>()
            ApiResponse.Success(response)
        } catch (e: Exception) {
            ApiResponse.Error(e)
        }
    }

}