package com.bold.main_weather.data.map

import com.bold.main_weather.data.api.CityNextDaysModel
import com.bold.main_weather.data.api.dto.CitySearchResponseDto
import com.bold.main_weather.data.api.dto.nextDays.CityNextDaysResponseDto
import com.bold.main_weather.data.api.dto.nextDays.toCurrentModel
import com.bold.main_weather.data.api.dto.nextDays.toForecastModel
import com.bold.main_weather.data.repo.models.CitySearchModel
import com.bold.network.ApiResponse

fun CitySearchResponseDto.toModel(): CitySearchModel {
    return CitySearchModel(
        name = this.name, url = this.url, country = this.country
    )
}

fun ApiResponse<List<CitySearchResponseDto>>.mapToModel(): ApiResponse<List<CitySearchModel>> {
    return when (this) {
        is ApiResponse.Success -> {
            ApiResponse.Success(data.map { it.toModel() })
        }

        is ApiResponse.Error -> this
        is ApiResponse.Loading -> this
    }
}

fun ApiResponse<CityNextDaysResponseDto>.mapToModelNext(): ApiResponse<CityNextDaysModel> {
    return when (this) {
        is ApiResponse.Success -> {
            val current = data.current.toCurrentModel()
            val forecast = data.forecast.toForecastModel()
            ApiResponse.Success(CityNextDaysModel(current, forecast))
        }
        is ApiResponse.Error -> this
        is ApiResponse.Loading -> this
    }
}