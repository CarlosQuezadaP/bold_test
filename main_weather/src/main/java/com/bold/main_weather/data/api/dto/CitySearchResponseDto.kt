package com.bold.main_weather.data.api.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CitySearchResponseDto(
    @SerialName("country")
    val country: String,
    @SerialName("name")
    val name: String,
    @SerialName("url")
    val url: String
)