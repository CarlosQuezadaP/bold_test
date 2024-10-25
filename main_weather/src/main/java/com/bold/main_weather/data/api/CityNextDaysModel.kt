package com.bold.main_weather.data.api

import com.bold.main_weather.data.api.dto.nextDays.CurrentModel
import com.bold.main_weather.data.api.dto.nextDays.ForecastModel

data class CityNextDaysModel(val current: CurrentModel, val forecast: ForecastModel)
