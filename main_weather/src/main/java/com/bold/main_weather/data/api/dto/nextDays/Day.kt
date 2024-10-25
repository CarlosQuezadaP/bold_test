package com.bold.main_weather.data.api.dto.nextDays

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Day(
    @SerialName("avghumidity") val avghumidity: Int,

    @SerialName("avgtemp_c") val avgtempC: Double,

    @SerialName("avgvis_km") val avgvisKm: Double,

    @SerialName("condition") val condition: Condition,
)

data class DayModel(
    val avghumidity: String,

    val avgtempC: String,

    val avgvisKm: String,

    val condition: Condition,
)

fun Day.toDayModel(): DayModel {
    return DayModel(
        avghumidity = this.avghumidity.toString(),
        avgtempC = this.avgtempC.toInt().toString(),
        avgvisKm = this.avgvisKm.toInt().toString(),
        condition = this.condition
    )
}