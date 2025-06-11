package com.sanaa.myweather.domain.usecase

import android.Manifest
import androidx.annotation.RequiresPermission
import com.sanaa.myweather.domain.model.WeatherInfo
import com.sanaa.myweather.domain.repository.WeatherRepository

class GetWeatherInfoUseCase(
    private val repository: WeatherRepository,
) {
    @RequiresPermission(allOf = [Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION])
    suspend fun getCurrentWeather(lat: Double, long: Double): WeatherInfo {
        return repository.getWeatherInfo(
            lat = lat,
            long = long
        )
    }
}