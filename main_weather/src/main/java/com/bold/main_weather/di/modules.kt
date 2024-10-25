package com.bold.main_weather.di

import com.bold.main_weather.data.api.IWeatherApi
import com.bold.main_weather.data.api.WeatherApi
import com.bold.main_weather.data.repo.IWeatherRepo
import com.bold.main_weather.data.repo.WeatherRepo
import com.bold.main_weather.presentation.viewmodel.HomeViewModel
import com.bold.main_weather.presentation.viewmodel.WeatherViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val apiModule = module {
    single<IWeatherApi> {
        WeatherApi(get())
    }
}


val repoModule = module {
    single<IWeatherRepo> {
        WeatherRepo(get())
    }
}

val viewModelModule = module {
    viewModel { WeatherViewModel(get()) }
    viewModel { HomeViewModel(get()) }
}

