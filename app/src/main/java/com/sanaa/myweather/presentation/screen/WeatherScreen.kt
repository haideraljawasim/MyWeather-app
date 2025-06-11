package com.sanaa.myweather.presentation.screen

import android.Manifest
import androidx.annotation.RequiresPermission
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sanaa.myweather.presentation.components.CurrentWeatherCard
import com.sanaa.myweather.presentation.componets.DailyWeatherColumn
import com.sanaa.myweather.presentation.componets.HourlyWeatherRow
import com.sanaa.myweather.presentation.componets.LocationDisplayCard
import com.sanaa.myweather.presentation.componets.WeatherStatusGrid
import com.sanaa.myweather.presentation.theme.LocalWeatherColors
import com.sanaa.myweather.presentation.theme.MyWeatherTheme
import com.sanaa.myweather.presentation.theme.urbanistFamily
import com.sanaa.myweather.presentation.viewModel.WeatherViewModel
import com.sanaa.myweather.presentation.viewModel.state.WeatherUiState
import org.koin.androidx.compose.koinViewModel

@RequiresPermission(allOf = [Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION])
@Composable
fun WeatherScreen(
    modifier: Modifier = Modifier,
    inPadding: PaddingValues,
    viewModel: WeatherViewModel = koinViewModel<WeatherViewModel>(),
) {
    val state = viewModel.state.collectAsState()

    val colors = LocalWeatherColors.current
    val scrollState = rememberScrollState()
    val rawScroll = scrollState.value.toFloat()
    val maxScrollPx = 120f
    val scrollProgress = (rawScroll / maxScrollPx).coerceIn(0f, 1f)

    when (val uiState = state.value) {
        is WeatherUiState.Loading -> {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(colors.primaryBackgroundColor),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CircularProgressIndicator(
                    color = colors.imageBlurColor
                )
            }
        }

        is WeatherUiState.Error -> {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(colors.primaryBackgroundColor)
                ,
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "something went wrong",
                    color = colors.imageBlurColor,
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    fontFamily = urbanistFamily,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 32.sp
                )

            }
        }

        is WeatherUiState.Success -> {
            MyWeatherTheme(isDay = uiState.weatherUiInfo.isDay) {

                Column(
                    modifier = modifier
                        .fillMaxSize()
                        .background(colors.primaryBackgroundColor)
                        .padding(inPadding)
                        .verticalScroll(scrollState),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Spacer(modifier = Modifier.height(24.dp))

                    LocationDisplayCard(
                        cityName = uiState.weatherUiInfo.cityName,
                        modifier = Modifier
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    CurrentWeatherCard(
                        currentWeather = uiState.weatherUiInfo,
                        modifier = Modifier,
                        scrollState = scrollProgress
                    )


                    Spacer(modifier = Modifier.height(12.dp))

                    WeatherStatusGrid(
                        weatherUiInfo = uiState.weatherUiInfo
                    )
                    Spacer(modifier = Modifier.height(24.dp))

                    HourlyWeatherRow(
                        hourlyWeather = uiState.weatherUiInfo.hourlyWeather,
                        unit = uiState.weatherUiInfo.weatherUnitsUiState.temperature
                    )

                    Spacer(modifier = Modifier.height(24.dp))

                    DailyWeatherColumn(
                        dailyWeather = uiState.weatherUiInfo.dailyWeatherUiState,
                        unit = uiState.weatherUiInfo.weatherUnitsUiState.temperature
                    )
                    Spacer(modifier = Modifier.height(24.dp))
                }
            }
        }
    }
}