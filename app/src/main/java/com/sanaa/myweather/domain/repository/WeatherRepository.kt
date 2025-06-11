package com.sanaa.myweather.domain.repository

import com.sanaa.myweather.domain.model.WeatherInfo

interface WeatherRepository {
    suspend fun getWeatherInfo(lat: Double, long: Double): WeatherInfo
}