package com.sanaa.myweather.presentation.viewModel.state

data class DailyWeatherUiState(
    val day: String,
    val weatherIcon: Int,
    val highTemperature: String,
    val lowTemperature: String,
)