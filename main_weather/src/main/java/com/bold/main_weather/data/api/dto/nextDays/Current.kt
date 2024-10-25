package com.bold.main_weather.data.api.dto.nextDays


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.text.SimpleDateFormat
import java.util.Locale

@Serializable
data class Current(
    @SerialName("temp_c")
    val tempC: Double,
    @SerialName("condition")
    val condition: Condition,
    @SerialName("wind_kph")
    val windKph: Double,
    @SerialName("humidity")
    val humidity: Int,
    @SerialName("last_updated")
    val lastUpdated: String
)

@Serializable
data class Condition(
    @SerialName("code")
    val code: Int,
    @SerialName("icon")
    val icon: String,
    @SerialName("text")
    val text: String
)

data class CurrentModel(
    val tempC: Double,
    val condition: ConditionModel,
    val windKph: Double,
    val humidity: Int,
    val lastUpdated: String
)

data class ConditionModel(
    val code: Int,
    val icon: String,
    val text: String
)

fun Current.toCurrentModel(): CurrentModel {
    return CurrentModel(
        tempC = this.tempC,
        condition = this.condition.toConditionModel(),
        windKph = this.windKph,
        humidity = this.humidity,
        lastUpdated = formatDate(this.lastUpdated)
    )
}

fun Condition.toConditionModel(): ConditionModel {
    return ConditionModel(
        code = code,
        icon = icon,
        text = text
    )
}


fun formatDate(dateString: String): String {
    val day = dateString.split(" ")[0]
    val inputFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    val outputFormat =
        SimpleDateFormat("dd MMMM, yyyy", Locale.getDefault())
    val date = inputFormat.parse(day)
    return outputFormat.format(date)
}


