package com.bold.main_weather.data.api.dto.nextDays

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CityNextDaysResponseDto(
    @SerialName("current")
    val current: Current,
    @SerialName("forecast")
    val forecast: Forecast

)