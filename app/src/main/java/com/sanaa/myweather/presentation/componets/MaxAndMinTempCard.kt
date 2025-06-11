package com.sanaa.myweather.presentation.componets

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
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
fun MaxAndMinTempCard(
    modifier: Modifier = Modifier,
    highTemp: String,
    lowTemp: String,
    unit: String = "C"
) {
    Row(
        modifier = modifier
            .background(
                color = LocalWeatherColors.current.minMaxTempBackgroundColor,
                shape = RoundedCornerShape(100.dp)
            )
            .padding(vertical = 8.dp, horizontal = 24.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Row(
            modifier = Modifier,
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(2.dp)
        ) {
            Icon(
                painter = painterResource(R.drawable.arrow_up_small),
                contentDescription = null,
                tint = LocalWeatherColors.current.minMaxTempTextColor
            )
            Text(
                text = "${highTemp}${unit}",
                color = LocalWeatherColors.current.minMaxTempTextColor,
                fontFamily = urbanistFamily,
                fontWeight = FontWeight.Medium,
                fontSize = 16.sp
            )
        }
        Icon(
            painter = painterResource(R.drawable.border_line),
            contentDescription = null,
            tint = LocalWeatherColors.current.minMaxTempTextColor,
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
                tint = LocalWeatherColors.current.minMaxTempTextColor
            )
            Text(
                text = "${lowTemp}${unit}",
                color = LocalWeatherColors.current.minMaxTempTextColor,
                fontFamily = urbanistFamily,
                fontWeight = FontWeight.Medium,
                fontSize = 16.sp
            )
        }
    }

}

@Preview
@Composable
private fun PreviewMaxAndMinTempCard() {
    MaxAndMinTempCard(highTemp = "25", lowTemp = "15")
}