package com.bold.main_weather.data.api.dto.nextDays

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Forecast(
    @SerialName("forecastday")
    val forecastday: List<Forecastday>
)


data class ForecastModel(
    val forecastday: List<ForecastdayModel>
)


fun Forecast.toForecastModel(): ForecastModel {
    return ForecastModel(
        forecastday = this.forecastday.map { it.toForecastModel() }
    )
}