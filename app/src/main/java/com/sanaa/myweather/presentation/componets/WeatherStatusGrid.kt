package com.sanaa.myweather.presentation.componets

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.sanaa.myweather.R
import com.sanaa.myweather.presentation.viewModel.state.WeatherUiInfo

@Composable
fun WeatherStatusGrid(
    modifier: Modifier = Modifier,
    weatherUiInfo: WeatherUiInfo,

    ) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(minSize = 108.dp),
        contentPadding = PaddingValues(12.dp),
        horizontalArrangement = Arrangement.spacedBy(6.dp),
        verticalArrangement = Arrangement.spacedBy(6.dp),
        modifier = modifier
            .heightIn(max = 400.dp)
            .fillMaxWidth(),
        userScrollEnabled = false,


        ) {
        item {
            StatusWeatherCard(
                icon = painterResource(R.drawable.fast_wind),
                title = "Wind",
                value = "${weatherUiInfo.windSpeed} ${weatherUiInfo.weatherUnitsUiState.windSpeed}"
            )
        }
        item {
            StatusWeatherCard(
                icon = painterResource(R.drawable.humidity),
                title = "Humidity",
                value = "${weatherUiInfo.humidity}${weatherUiInfo.weatherUnitsUiState.humidity}"
            )
        }
        item {
            StatusWeatherCard(
                icon = painterResource(R.drawable.rain),
                title = "Rain",
                value = "${weatherUiInfo.rain} ${weatherUiInfo.weatherUnitsUiState.rain}"
            )

        }
        item {
            StatusWeatherCard(
                icon = painterResource(R.drawable.uv),
                title = "UV Index",
                value = weatherUiInfo.uvIndex
            )
        }
        item {
            StatusWeatherCard(
                icon = painterResource(R.drawable.arrow_down),
                title = "Pressure",
                value = "${weatherUiInfo.pressure} ${weatherUiInfo.weatherUnitsUiState.pressure}"
            )
        }
        item {
            StatusWeatherCard(
                icon = painterResource(R.drawable.temperature),
                title = "Feels like",
                value = "${weatherUiInfo.apparentTemperature}${weatherUiInfo.weatherUnitsUiState.apparentTemperature}"
            )
        }
    }

}