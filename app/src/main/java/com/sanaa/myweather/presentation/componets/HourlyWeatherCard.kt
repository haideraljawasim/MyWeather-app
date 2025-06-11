package com.sanaa.myweather.presentation.componets

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sanaa.myweather.presentation.composable.dropShadow
import com.sanaa.myweather.presentation.theme.LocalWeatherColors
import com.sanaa.myweather.presentation.theme.urbanistFamily
import com.sanaa.myweather.presentation.viewModel.state.HourlyWeatherStateUi

@Composable
fun HourlyWeatherCard(
    hourlyWeather: HourlyWeatherStateUi,
    modifier: Modifier = Modifier,
    unit: String
) {
    val  colors = LocalWeatherColors.current
    Box(
        modifier = Modifier.height(132.dp)
    ) {
        Column(
            modifier = modifier
                .background(
                    color = colors.todayCardBackgroundColor,
                    shape = RoundedCornerShape(20.dp)
                )
                .border(
                    width = 1.dp,
                    color = colors.todayCardBorderColor,
                    shape = RoundedCornerShape(20.dp)
                )
                .height(120.dp)
                .width(88.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Bottom
        ) {
            Text(
                text = "${hourlyWeather.temperature}${unit}",
                fontFamily = urbanistFamily,
                fontWeight = FontWeight.Medium,
                fontSize = 16.sp,
                color = colors.todayCardValueFontColor
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "${hourlyWeather.time}:00",
                fontFamily = urbanistFamily,
                fontWeight = FontWeight.Medium,
                fontSize = 16.sp,
                modifier = Modifier.padding(bottom = 16.dp),
                color = colors.todayCardTitleFontColor
            )
        }

        val blurColor = if (hourlyWeather.isDay) {
          colors.smallBlurColor
        } else {
            Color.Transparent
        }
        Image(
            painter = painterResource(id = hourlyWeather.weatherIcon),
            contentDescription = null,
            modifier = Modifier
                .align(Alignment.TopCenter)
                .offset(y = (-12).dp)
                .size(height = 58.dp, width = 64.dp)
                .dropShadow(
                    shape = CircleShape,
                    color = blurColor,
                    blur = 10.dp,
                    spread = 1.dp
                ),
            contentScale = ContentScale.FillWidth
        )
    }

}
