package com.sanaa.myweather.data.remote

import com.sanaa.myweather.data.remote.helpers.openMeteUrlBuilder
import com.sanaa.myweather.domain.model.ErrorFetchingWeatherInfoException
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get


class OpenMeteWeatherApi(
    private val client: HttpClient
) {
    suspend fun getWeatherInto(lat: Double, long: Double): WeatherApiResponse {
        try {
            val response = client.get(openMeteUrlBuilder(lat, long))
            return response.body<WeatherApiResponse>()
        } catch (_: Exception) {

            throw ErrorFetchingWeatherInfoException()
        }
    }
}