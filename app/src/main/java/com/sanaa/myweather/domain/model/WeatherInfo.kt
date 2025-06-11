package com.sanaa.myweather.domain.model
import kotlinx.datetime.LocalDateTime

data class WeatherInfo(
    val currentTemperature: Double,
    val highTemperature: Double,
    val lowTemperature: Double,
    val windSpeed: Double,
    val humidityPercentage: Double,
    val pressure: Double,
    val apparentTemperature: Double,
    val rainChance: Double,
    val weatherCode: Int,
    val isDay: Boolean,
    val hourlyWeatherForecast: List<HourlyWeatherForecast>,
    val dailyWeatherForecast: List<DailyWeatherForecast>,
    val units: WeatherUnits
)

data class HourlyWeatherForecast(
    val temperature: Double,
    val time: java.time.LocalDateTime,
    val weatherCode: Int,
    val isDay: Boolean
)
data class DailyWeatherForecast(
    val highTemperature: Double,
    val lowTemperature: Double,
    val date: LocalDateTime,
    val weatherCode: Int,
    val uvIndex: Double
)

data class WeatherUnits(
    val temperatureUnit: String,
    val windSpeedUnit: String,
    val humidityPercentageUnit: String,
    val pressureUnit: String,
    val apparentTemperatureUnit: String,
    val rainChanceUnit: String
)