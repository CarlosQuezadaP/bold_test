package com.bold.main_weather.data.repository

import com.bold.main_weather.data.api.CityNextDaysModel
import com.bold.main_weather.data.api.IWeatherApi
import com.bold.main_weather.data.api.dto.CitySearchResponseDto
import com.bold.main_weather.data.api.dto.nextDays.CityNextDaysResponseDto
import com.bold.main_weather.data.api.dto.nextDays.Condition
import com.bold.main_weather.data.api.dto.nextDays.ConditionModel
import com.bold.main_weather.data.api.dto.nextDays.Current
import com.bold.main_weather.data.api.dto.nextDays.CurrentModel
import com.bold.main_weather.data.api.dto.nextDays.Day
import com.bold.main_weather.data.api.dto.nextDays.DayModel
import com.bold.main_weather.data.api.dto.nextDays.Forecast
import com.bold.main_weather.data.api.dto.nextDays.ForecastModel
import com.bold.main_weather.data.api.dto.nextDays.Forecastday
import com.bold.main_weather.data.api.dto.nextDays.ForecastdayModel
import com.bold.main_weather.data.repo.WeatherRepo
import com.bold.main_weather.data.repo.models.CitySearchModel
import com.bold.network.ApiResponse
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class WeatherRepoTest {

    private lateinit var weatherRepo: WeatherRepo
    private lateinit var weatherApi: IWeatherApi

    @Before
    fun setup() {
        weatherApi = mockk()
        weatherRepo = WeatherRepo(weatherApi)
    }


    @Test
    fun `todayWeather should return ApiResponse Success with CitySearchModel list on successful API response`() =
        runTest {
            val cityName = "UK"
            val expectedResponse =
                ApiResponse.Success(listOf(CitySearchModel("London", "UK", "url")))

            val apiResponse =
                ApiResponse.Success(listOf(CitySearchResponseDto("London", "UK", "url")))
            coEvery { weatherApi.todayWeather(cityName) } returns apiResponse

            val actualResponse = weatherRepo.todayWeather(cityName)

            Assert.assertEquals(expectedResponse, actualResponse)
        }


    @Test
    fun `nextDaysWeather should return ApiResponse Success with CityNextDaysModel on successful API response`() =
        runTest {
            val urlCity = "london"
            val condition = Condition(1000, "icon_url", "Sunny")
            val dayModel = DayModel("60", "25", "10", condition)
            val forecastdayModel =
                ForecastdayModel("27 octubre, 2023", dayModel) // Cambio en el formato de fecha
            val forecastModel = ForecastModel(listOf(forecastdayModel))
            val currentModel =
                CurrentModel(
                    25.0,
                    ConditionModel(1000, "icon_url", "Sunny"),
                    10.0,
                    60,
                    "27 octubre, 2023"
                ) // Cambio en el formato de fecha
            val cityNextDaysModel = CityNextDaysModel(currentModel, forecastModel)

            val cityNextDaysResponseDto = CityNextDaysResponseDto(
                current = Current(25.0, condition, 10.0, 60, "2023-10-27 10:00"),
                forecast = Forecast(
                    listOf(
                        Forecastday(
                            "2023-10-27",
                            Day(60, 25.0, 10.0, condition)
                        )
                    )
                )
            )

            val apiResponse: ApiResponse<CityNextDaysResponseDto> =
                ApiResponse.Success(cityNextDaysResponseDto)
            coEvery { weatherApi.nextDaysWeather(urlCity) } returns apiResponse

            val actualResponse = weatherRepo.nextDaysWeather(urlCity)

            val expectedResponse: ApiResponse<CityNextDaysModel> =
                ApiResponse.Success(cityNextDaysModel)
            Assert.assertEquals(expectedResponse, actualResponse)
        }

    @Test
    fun `todayWeather should return ApiResponse Error on API error response`() = runTest {
        val cityName = "London"
        val exception = Exception("API Error")
        val expectedResponse = ApiResponse.Error(exception)
        coEvery { weatherApi.todayWeather(cityName) } returns expectedResponse

        val actualResponse = weatherRepo.todayWeather(cityName)

        Assert.assertEquals(expectedResponse, actualResponse)
    }

    @Test
    fun `todayWeather should return ApiResponse Error on Network error response`() = runTest {
        val cityName = "London"
        val exception = Exception("Network Error")
        val expectedResponse = ApiResponse.Error(exception)
        coEvery { weatherApi.todayWeather(cityName) } returns expectedResponse

        val actualResponse = weatherRepo.todayWeather(cityName)

        Assert.assertEquals(expectedResponse, actualResponse)
    }

    @Test
    fun `todayWeather should return ApiResponse Error on Unknown error response`() = runTest {
        val cityName = "London"
        val exception = Exception("Unknown Error")
        val expectedResponse = ApiResponse.Error(exception)
        coEvery { weatherApi.todayWeather(cityName) } returns expectedResponse

        val actualResponse = weatherRepo.todayWeather(cityName)

        Assert.assertEquals(expectedResponse, actualResponse)
    }

    // nextDaysWeather() Tests


    @Test
    fun `nextDaysWeather should return ApiResponse Error on API error response`() = runTest {
        val urlCity = "london"
        val exception = Exception("API Error")
        val expectedResponse = ApiResponse.Error(exception)
        coEvery { weatherApi.nextDaysWeather(urlCity) } returns expectedResponse

        val actualResponse = weatherRepo.nextDaysWeather(urlCity)

        Assert.assertEquals(expectedResponse, actualResponse)
    }

    @Test
    fun `nextDaysWeather should return ApiResponse Error on Network error response`() = runTest {
        val urlCity = "london"
        val exception = Exception("Network Error")
        val expectedResponse = ApiResponse.Error(exception)
        coEvery { weatherApi.nextDaysWeather(urlCity) } returns expectedResponse

        val actualResponse = weatherRepo.nextDaysWeather(urlCity)

        Assert.assertEquals(expectedResponse, actualResponse)
    }

    @Test
    fun `nextDaysWeather should return ApiResponse Error on Unknown error response`() = runTest {
        val urlCity = "london"
        val exception = Exception("Unknown Error")
        val expectedResponse = ApiResponse.Error(exception)
        coEvery { weatherApi.nextDaysWeather(urlCity) } returns expectedResponse

        val actualResponse = weatherRepo.nextDaysWeather(urlCity)

        Assert.assertEquals(expectedResponse, actualResponse)
    }


}