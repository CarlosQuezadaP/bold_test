package com.bold.main_weather.data.api.dto.nextDays

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Forecastday(
    @SerialName("date") val date: String, @SerialName("day") val day: Day
)

data class ForecastdayModel(
    val date: String, val day: DayModel, val isSelected: Boolean = false
)

fun Forecastday.toForecastModel(): ForecastdayModel {
    return ForecastdayModel(
        date = formatDate(this.date),
        day = this.day.toDayModel()
    )
}