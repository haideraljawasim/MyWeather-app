package com.sanaa.myweather.presentation.viewModel.state

data class HourlyWeatherStateUi(
    val temperature: String,
    val weatherIcon: Int,
    val time: String,
    val isDay: Boolean
)