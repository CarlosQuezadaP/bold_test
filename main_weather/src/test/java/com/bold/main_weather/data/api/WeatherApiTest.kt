package com.bold.main_weather.data.api

import com.bold.main_weather.data.api.dto.CitySearchResponseDto
import com.bold.network.ApiResponse
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.test.runTest
import org.junit.Test
import java.io.IOException

class WeatherApiTest {

    private val httpClient: HttpClient = mockk()
    private val response: HttpResponse = mockk()
    private val weatherApi = WeatherApi(httpClient)

    @Test
    fun `todayWeather should return Success with data when request is successful`() = runTest {
        val cityName = "London"
        val expectedResponse = listOf(CitySearchResponseDto("UK", "London", "UK"))

        coEvery { httpClient.get {
            url {
                path("search.json")
                parameters.append("q", cityName)
            }
        } } returns response

        coEvery { response.body<List<CitySearchResponseDto>>() } returns expectedResponse

        val actualResponse = weatherApi.todayWeather(cityName)

        assertEquals(ApiResponse.Success(expectedResponse), actualResponse)
    }

    @Test
    fun `todayWeather should return Error when request throws exception`() = runTest {
        val cityName = "London"
        val exception = IOException("Network error")

        coEvery { httpClient.get {
            url {
                path("search.json")
                parameters.append("q", cityName)
            }
        } } throws exception

        val actualResponse = weatherApi.todayWeather(cityName)

        assertEquals(ApiResponse.Error(exception), actualResponse)
    }

    @Test
    fun `todayWeather should return Success with empty list when response is empty`() = runTest {
        val cityName = "London"
        val expectedResponse = emptyList<CitySearchResponseDto>()

        coEvery { httpClient.get {
            url {
                path("search.json")
                parameters.append("q", cityName)
            }
        } } returns response

        coEvery { response.body<List<CitySearchResponseDto>>() } returns expectedResponse

        val actualResponse = weatherApi.todayWeather(cityName)

        assertEquals(ApiResponse.Success(expectedResponse), actualResponse)
    }
}