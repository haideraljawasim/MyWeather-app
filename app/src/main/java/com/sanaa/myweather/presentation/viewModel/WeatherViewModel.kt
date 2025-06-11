package com.sanaa.myweather.presentation.viewModel

import android.Manifest
import androidx.annotation.RequiresPermission
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sanaa.myweather.domain.repository.LocationRepository
import com.sanaa.myweather.domain.usecase.GetCurrentLocationUseCase
import com.sanaa.myweather.domain.usecase.GetWeatherInfoUseCase
import com.sanaa.myweather.presentation.viewModel.mapper.mapWeatherInfoToWeatherStateUi
import com.sanaa.myweather.presentation.viewModel.state.WeatherUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch


class WeatherViewModel(
    private val getWeatherInfoUseCase: GetWeatherInfoUseCase,
    private val locationRepository: GetCurrentLocationUseCase
) : ViewModel() {

    private val _state = MutableStateFlow<WeatherUiState>(WeatherUiState.Loading)
    val state = _state.asStateFlow()

    @RequiresPermission(
        allOf = [
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION
        ]
    )
    fun loadWeather() {
        viewModelScope.launch {
            _state.value = WeatherUiState.Loading
            try {
                val location = locationRepository.getCurrentLocation()
                val weatherInfo = getWeatherInfoUseCase.getCurrentWeather(
                    lat = location.latitude,
                    long = location.longitude
                )
                _state.value =
                    WeatherUiState.Success(
                        weatherUiInfo = mapWeatherInfoToWeatherStateUi(
                            weatherInfo = weatherInfo,
                            cityName = location.cityName
                        )
                    )
            } catch (_: Exception) {
                _state.value = WeatherUiState.Error("Error fetching weather")
            }
        }
    }
}