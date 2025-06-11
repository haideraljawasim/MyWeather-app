package com.sanaa.myweather.data.remote

import kotlinx.serialization.Serializable

@Serializable
data class WeatherApiResponse(
    val current: CurrentWeatherDto,
    val daily: DailyForecastDto,
    val hourly: HourlyForecastDto,
    val current_units: CurrentUnits
)

@Serializable
data class DailyForecastDto(
    val time: List<String>,
    val temperature_2m_max: List<Double>,
    val temperature_2m_min: List<Double>,
    val weather_code: List<Int>,
    val uv_index_max: List<Double>
)

@Serializable
data class HourlyForecastDto(
    val time: List<String>,
    val temperature_2m: List<Double>,
    val weather_code: List<Int>,
    val is_day: List<Int>,
)

@Serializable
data class CurrentWeatherDto(
    val time: String,
    val temperature_2m: Double,
    val weather_code: Int,
    val wind_speed_10m: Double,
    val relative_humidity_2m: Double,
    val apparent_temperature: Double,
    val rain: Double,
    val surface_pressure: Double,
    val is_day: Int,
    val precipitation_probability: Double
)

@Serializable
data class CurrentUnits(
    val temperature_2m: String,
    val wind_speed_10m: String,
    val relative_humidity_2m: String,
    val apparent_temperature: String,
    val precipitation_probability: String,
    val surface_pressure: String
)