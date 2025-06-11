package com.sanaa.myweather.presentation.componets

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sanaa.myweather.presentation.theme.LocalWeatherColors
import com.sanaa.myweather.presentation.theme.urbanistFamily
import com.sanaa.myweather.presentation.viewModel.state.HourlyWeatherStateUi

@Composable
fun HourlyWeatherRow(
    modifier: Modifier = Modifier,
    hourlyWeather: List<HourlyWeatherStateUi>,
    unit: String
) {
    Column(
        modifier = modifier
    ) {
        Text(
            text = "Today",
            fontWeight = FontWeight.SemiBold,
            fontSize = 20.sp,
            fontFamily = urbanistFamily,
            modifier = Modifier.padding(start = 16.dp),
            color = LocalWeatherColors.current.todayLabelFontColor
        )
        Spacer(modifier = Modifier.height(24.dp))
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            contentPadding = PaddingValues(horizontal = 16.dp),
        ) {
            items(hourlyWeather.size) {
                HourlyWeatherCard(hourlyWeather = hourlyWeather[it], unit = unit)
            }
        }
    }
}