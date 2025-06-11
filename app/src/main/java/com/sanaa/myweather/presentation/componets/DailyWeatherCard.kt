package com.sanaa.myweather.presentation.componets

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sanaa.myweather.R
import com.sanaa.myweather.presentation.theme.LocalWeatherColors
import com.sanaa.myweather.presentation.theme.urbanistFamily
import com.sanaa.myweather.presentation.viewModel.state.DailyWeatherUiState

@Composable
fun DailyWeatherCard(
    modifier: Modifier = Modifier,
    dailyWeather: DailyWeatherUiState,
    unit: String
) {

    val colors = LocalWeatherColors.current
    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .height(61.dp)

    ) {
        Text(
            text = dailyWeather.day,
            fontFamily = urbanistFamily,
            color = colors.nextDaysCardTitleFontColor,
            fontWeight = FontWeight.Normal,
            fontSize = 16.sp,
            modifier = Modifier.align(alignment = Alignment.CenterStart)
        )

        Image(
            painter = painterResource(dailyWeather.weatherIcon),
            contentDescription = null,
            modifier = Modifier
                .size(32.dp)
                .align(alignment = Alignment.Center)
        )

        Row(
            modifier = Modifier.align(alignment = Alignment.CenterEnd),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Row(
                modifier = Modifier,
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(2.dp)
            ) {
                Icon(
                    painter = painterResource(R.drawable.arrow_up_small),
                    contentDescription = null,
                    tint = colors.minMaxTempTextColor
                )
                Text(
                    text = "${dailyWeather.highTemperature}${unit}",
                    color = LocalWeatherColors.current.minMaxTempTextColor,
                    fontFamily = urbanistFamily,
                    fontWeight = FontWeight.Medium,
                    fontSize = 14.sp
                )
            }
            Icon(
                painter = painterResource(R.drawable.border_line),
                contentDescription = null,
                tint = colors.minMaxTempTextColor,
                modifier = Modifier.padding(horizontal = 8.dp)
            )
            Row(
                modifier = Modifier,
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(2.dp)
            ) {
                Icon(
                    painter = painterResource(R.drawable.arrow_down_small),
                    contentDescription = null,
                    tint = colors.minMaxTempTextColor
                )
                Text(
                    text = "${dailyWeather.lowTemperature}${unit}",
                    color = colors.minMaxTempTextColor,
                    fontFamily = urbanistFamily,
                    fontWeight = FontWeight.Medium,
                    fontSize = 14.sp
                )
            }
        }

    }

}

