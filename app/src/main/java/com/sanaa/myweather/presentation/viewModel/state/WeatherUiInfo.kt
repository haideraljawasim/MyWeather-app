package com.sanaa.myweather.presentation.viewModel.state

import com.sanaa.myweather.R


sealed class WeatherUiState {
    object Loading : WeatherUiState()
    data class Error(val message: String) : WeatherUiState()
    data class Success(val weatherUiInfo: WeatherUiInfo) : WeatherUiState()
}


data class WeatherUiInfo(
    val cityName: String = "",
    val temperature: String = "",
    val weatherCondition: String = "",
    val weatherIcon: Int = R.drawable.clear_sky_day,
    val humidity: String = "",
    val windSpeed: String = "",
    val apparentTemperature: String = "",
    val uvIndex: String = "",
    val pressure: String = "",
    val isDay: Boolean = false,
    val rain: String = "",
    val maxTemperature: String = "",
    val minTemperature: String = "",
    val hourlyWeather: List<HourlyWeatherStateUi> = emptyList(),
    val dailyWeatherUiState: List<DailyWeatherUiState> = emptyList(),
    val weatherUnitsUiState: WeatherUnitsUiState = WeatherUnitsUiState(
        temperature = "",
        windSpeed = "",
        humidity = "",
        pressure = "",
        apparentTemperature = "",
        rain = ""
    )

)





