package com.sanaa.myweather.presentation.components

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.lerp
import androidx.compose.ui.unit.sp
import com.sanaa.myweather.presentation.componets.MaxAndMinTempCard
import com.sanaa.myweather.presentation.composable.animateAlignmentAsState
import com.sanaa.myweather.presentation.composable.dropShadow
import com.sanaa.myweather.presentation.theme.LocalWeatherColors
import com.sanaa.myweather.presentation.theme.urbanistFamily
import com.sanaa.myweather.presentation.viewModel.state.WeatherUiInfo

@Composable
fun CurrentWeatherCard(
    modifier: Modifier = Modifier,
    currentWeather: WeatherUiInfo,
    scrollState: Float,
) {


    val imageHeight = lerp(200.dp, 112.dp, scrollState)
    val imageWidth = lerp(220.dp, 124.dp, scrollState)
    val easedScrollProgress = scrollState.coerceIn(0f, 1f)
    val animatedHeight by animateDpAsState(
        targetValue = lerp(355.dp, 155.dp, easedScrollProgress),
    )
    val weatherImageTargetAlignment =
        if (easedScrollProgress < 0.4f) Alignment.TopCenter else Alignment.CenterStart

    val currentWeatherDetailsTargetAlignment =
        if (easedScrollProgress < 0.4f) Alignment.BottomCenter else Alignment.CenterEnd

    val weatherImageAlignment by animateAlignmentAsState(weatherImageTargetAlignment)
    val currentWeatherDetailsAlignment by animateAlignmentAsState(
        currentWeatherDetailsTargetAlignment
    )

    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(animatedHeight)
            .padding(horizontal = 12.dp)
    ) {

        Box(
            modifier = Modifier.align(weatherImageAlignment),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = currentWeather.weatherIcon),
                contentDescription = null,
                contentScale = ContentScale.FillBounds,
                modifier = modifier
                    .height(imageHeight)
                    .width(imageWidth)
                    .dropShadow(
                        shape = CircleShape,
                        color = LocalWeatherColors.current.imageBlurColor,
                        blur = 150.dp,
                        spread = 1.dp
                    )

            )
        }

        Column(
            modifier = Modifier.align(currentWeatherDetailsAlignment),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = "${currentWeather.temperature}${currentWeather.weatherUnitsUiState.temperature}",
                color = LocalWeatherColors.current.temperatureTextColor,
                fontFamily = urbanistFamily,
                fontSize = 64.sp,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.padding(top = 12.dp)
            )
            Text(
                text = currentWeather.weatherCondition,
                color = LocalWeatherColors.current.weatherConditionTextColor,
                fontFamily = urbanistFamily,
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                modifier = Modifier
            )
            Box {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .align(Alignment.Center)
                        .padding(horizontal = 24.dp, vertical = 8.dp)
                ) {

                    MaxAndMinTempCard(
                        highTemp = currentWeather.maxTemperature,
                        lowTemp = currentWeather.minTemperature,
                        unit = currentWeather.weatherUnitsUiState.temperature
                    )
                }
            }
        }
    }
}
