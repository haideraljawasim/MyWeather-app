package com.sanaa.myweather.presentation.componets

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sanaa.myweather.R
import com.sanaa.myweather.presentation.theme.LocalWeatherColors
import com.sanaa.myweather.presentation.theme.urbanistFamily

@Composable
fun LocationDisplayCard(
    cityName: String,
    modifier: Modifier = Modifier
) {

    val colors = LocalWeatherColors.current

    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(4.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(R.drawable.location),
                contentDescription = null,
                tint = colors.locationIconColor
            )
            Text(
                text = cityName,
                color = colors.locationFontColor,
                fontWeight = FontWeight.Medium,
                fontSize = 16.sp,
                fontFamily = urbanistFamily,
                lineHeight = 20.sp
            )
        }
    }
}

@Preview
@Composable
private fun WeatherCardPreview() {
    LocationDisplayCard(
        cityName = "Baghdad"
    )
}