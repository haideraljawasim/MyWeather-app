package com.sanaa.myweather.presentation.componets

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sanaa.myweather.R
import com.sanaa.myweather.presentation.theme.LocalWeatherColors
import com.sanaa.myweather.presentation.theme.urbanistFamily

@Composable
fun StatusWeatherCard(
    modifier: Modifier = Modifier,
    icon: Painter,
    title: String,
    value: String
) {
    val  colors = LocalWeatherColors.current

    Column(
        modifier = modifier
            .background(
                color = colors.statusCardBackgroundColor,
                shape = RoundedCornerShape(24.dp)
            )
            .border(
                width = 1.dp,
                color = colors.statusCardBorderColor,
                shape = RoundedCornerShape(24.dp)
            )
            .height(115.dp)
            .padding(vertical = 16.dp, horizontal = 8.dp),
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        Image(
            painter = icon,
            contentDescription = title,
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = value,
            color = colors.statusCardValueFontColor,
            fontWeight = FontWeight.Medium,
            fontSize = 20.sp,
            fontFamily = urbanistFamily
        )
        Spacer(modifier = Modifier.height(2.dp))
        Text(
            text = title,
            color = colors.statusCardTitleFontColor,
            fontWeight = FontWeight.Normal,
            fontSize = 14.sp,
            fontFamily = urbanistFamily
        )
    }
}

@Preview
@Composable
private fun PreviewStatusWeatherCard() {
    StatusWeatherCard(
        icon = painterResource(R.drawable.humidity),
        title = "Humidity",
        value = "80%"
    )
}