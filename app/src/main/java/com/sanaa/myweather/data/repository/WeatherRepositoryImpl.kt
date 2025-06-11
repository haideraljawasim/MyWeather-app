package com.sanaa.myweather.data.repository

import com.sanaa.myweather.data.remote.OpenMeteWeatherApi
import com.sanaa.myweather.data.repository.mappers.toDomain
import com.sanaa.myweather.domain.model.WeatherInfo
import com.sanaa.myweather.domain.repository.WeatherRepository

class WeatherRepositoryImpl(
    private val dataSource: OpenMeteWeatherApi
) : WeatherRepository {
    override suspend fun getWeatherInfo(lat: Double, long: Double): WeatherInfo {
        val weather = dataSource.getWeatherInto(lat = lat, long = long)
        return weather.toDomain()
    }
}

