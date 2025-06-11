package com.sanaa.myweather.presentation.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color


val LocalWeatherColors = staticCompositionLocalOf { lightWeatherThemeColors() }

@Composable
fun MyWeatherTheme(
    isDay: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = if (isDay) {
        lightWeatherThemeColors()
    } else {
        darkWeatherThemeColors()
    }

    CompositionLocalProvider(LocalWeatherColors provides colorScheme) {
        content()
    }
}

fun darkWeatherThemeColors(
    backgroundColor: Brush = Brush.linearGradient(listOf(Color(0xFF060414), Color(0xFF0D0C19))),
    imageBlurColor: Color = Color(0x8074638A),
    smallBlurColor: Color = Color.Transparent,
    locationFontColor: Color = Color(0xFFFFFFFF),
    locationIconColor: Color = Color(0xFFFFFFFF),

    temperatureTextColor: Color = Color(0xFFFFFFFF),
    weatherConditionTextColor: Color = Color(0x99FFFFFF),

    minMaxTempBackgroundColor: Color = Color(0x14FFFFFF),
    minMaxTempTextColor: Color = Color(0xDEFFFFFF),

    statusCardBackgroundColor: Color = Color(0xB2060414),
    statusCardBorderColor: Color = Color(0x14FFFFFF),
    statusCardValueFontColor: Color = Color(0xDEFFFFFF),
    statusCardTitleFontColor: Color = Color(0x99FFFFFF),

    todayLabelFontColor: Color = Color(0xFFFFFFFF),
    todayCardBackgroundColor: Color = Color(0xB2060414),
    todayCardBorderColor: Color = Color(0x14FFFFFF),
    todayCardValueFontColor: Color = Color(0xDEFFFFFF),
    todayCardTitleFontColor: Color = Color(0x99FFFFFF),

    nextDaysBackgroundColor: Color = Color(0xB2060414),
    nextDaysLabelFontColor: Color = Color(0xFFFFFFFF),
    nextDaysCardTitleFontColor: Color = Color(0x99FFFFFF),
    nextDaysCardValueFontColor: Color = Color(0xDEFFFFFF),
    nextDaysCardValueBorderLineFontColor: Color = Color(0x14FFFFFF),
    nextDaysCardBorderColor: Color = Color(0x14FFFFFF),

    ): WeatherColorScheme =
    WeatherColorScheme(
        backgroundColor,
        imageBlurColor,
        smallBlurColor,
        locationFontColor,
        locationIconColor,
        temperatureTextColor,
        weatherConditionTextColor,
        minMaxTempBackgroundColor,
        minMaxTempTextColor,
        statusCardBackgroundColor,
        statusCardBorderColor,
        statusCardValueFontColor,
        statusCardTitleFontColor,
        todayLabelFontColor,
        todayCardBackgroundColor,
        todayCardBorderColor,
        todayCardValueFontColor,
        todayCardTitleFontColor,
        nextDaysBackgroundColor,
        nextDaysLabelFontColor,
        nextDaysCardTitleFontColor,
        nextDaysCardValueFontColor,
        nextDaysCardValueBorderLineFontColor,
        nextDaysCardBorderColor,
    )


fun lightWeatherThemeColors(
    backgroundColor: Brush = Brush.linearGradient(listOf(Color(0xFF87CEFA), Color(0xFFFFFFFF))),
    smallBlurColor: Color = Color(0x4DFFC701),
    imageBlurColor: Color = Color(0x8000619D),
    locationFontColor: Color = Color(0xFF323232),
    locationIconColor: Color = Color(0xFF323232),

    temperatureTextColor: Color = Color(0xFF060414),
    weatherConditionTextColor: Color = Color(0x99060414),

    minMaxTempBackgroundColor: Color = Color(0x14060414),
    minMaxTempTextColor: Color = Color(0x99060414),

    statusCardBackgroundColor: Color = Color(0xB2FFFFFF),
    statusCardBorderColor: Color = Color(0x14060414),
    statusCardValueFontColor: Color = Color(0xDE060414),
    statusCardTitleFontColor: Color = Color(0x99060414),

    todayLabelFontColor: Color = Color(0xFF060414),
    todayCardBackgroundColor: Color = Color(0xB2FFFFFF),
    todayCardBorderColor: Color = Color(0x14060414),
    todayCardValueFontColor: Color = Color(0xDE060414),
    todayCardTitleFontColor: Color = Color(0x99060414),


    nextDaysBackgroundColor: Color = Color(0xB2FFFFFF),
    nextDaysLabelFontColor: Color = Color(0xFF060414),
    nextDaysCardTitleFontColor: Color = Color(0x99060414),
    nextDaysCardValueFontColor: Color = Color(0xDE060414),
    nextDaysCardValueBorderLineFontColor: Color = Color(0x14FFFFFF),
    nextDaysCardBorderColor: Color = Color(0x14060414),

    ): WeatherColorScheme =
    WeatherColorScheme(
        backgroundColor,
        imageBlurColor,
        smallBlurColor,
        locationFontColor,
        locationIconColor,
        temperatureTextColor,
        weatherConditionTextColor,
        minMaxTempBackgroundColor,
        minMaxTempTextColor,
        statusCardBackgroundColor,
        statusCardBorderColor,
        statusCardValueFontColor,
        statusCardTitleFontColor,
        todayLabelFontColor,
        todayCardBackgroundColor,
        todayCardBorderColor,
        todayCardValueFontColor,
        todayCardTitleFontColor,
        nextDaysBackgroundColor,
        nextDaysLabelFontColor,
        nextDaysCardTitleFontColor,
        nextDaysCardValueFontColor,
        nextDaysCardValueBorderLineFontColor,
        nextDaysCardBorderColor,
    )


class WeatherColorScheme(
    val primaryBackgroundColor: Brush,
    val imageBlurColor: Color,
    val smallBlurColor: Color,

    val locationFontColor: Color,
    val locationIconColor: Color,

    val temperatureTextColor: Color,
    val weatherConditionTextColor: Color,

    val minMaxTempBackgroundColor: Color,
    val minMaxTempTextColor: Color,

    val statusCardBackgroundColor: Color,
    val statusCardBorderColor: Color,
    val statusCardValueFontColor: Color,
    val statusCardTitleFontColor: Color,

    val todayLabelFontColor: Color,
    val todayCardBackgroundColor: Color,
    val todayCardBorderColor: Color,
    val todayCardValueFontColor: Color,
    val todayCardTitleFontColor: Color,

    val nextDaysBackgroundColor: Color,
    val nextDaysLabelFontColor: Color,
    val nextDaysCardTitleFontColor: Color,
    val nextDaysCardValueFontColor: Color,
    val nextDaysCardValueBorderLineFontColor: Color,
    val nextDaysCardBorderColor: Color,

    )