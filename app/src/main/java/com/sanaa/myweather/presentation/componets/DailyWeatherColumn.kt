package com.sanaa.myweather.presentation.componets

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sanaa.myweather.presentation.theme.LocalWeatherColors
import com.sanaa.myweather.presentation.theme.urbanistFamily
import com.sanaa.myweather.presentation.viewModel.state.DailyWeatherUiState


@Composable
fun DailyWeatherColumn(
    modifier: Modifier = Modifier,
    dailyWeather: List<DailyWeatherUiState>,
    unit: String
) {

    val colors = LocalWeatherColors.current
    Column(
        modifier = modifier
    ) {
        Text(
            text = "Next 7 days",
            fontWeight = FontWeight.SemiBold,
            fontSize = 20.sp,
            fontFamily = urbanistFamily,
            modifier = Modifier.padding(start = 16.dp),
            color = colors.nextDaysLabelFontColor
        )
        Spacer(modifier = Modifier.height(12.dp))
        Column(
            modifier = Modifier
                .heightIn(max = 600.dp)
                .padding(horizontal = 16.dp)
                .clip(shape = RoundedCornerShape(24.dp))
                .border(
                    width = 1.dp,
                    color = colors.nextDaysCardBorderColor,
                    shape = RoundedCornerShape(24.dp)
                )
                .background(
                    colors.nextDaysBackgroundColor,
                    shape = RoundedCornerShape(24.dp)
                ),

            ) {
            dailyWeather.forEachIndexed { index, item ->
                DailyWeatherCard(dailyWeather = item, unit = unit)
                if (index != dailyWeather.lastIndex) {
                    HorizontalDivider(
                        color = colors.nextDaysCardBorderColor,
                        thickness = 1.dp
                    )
                }
            }
        }
    }
}

