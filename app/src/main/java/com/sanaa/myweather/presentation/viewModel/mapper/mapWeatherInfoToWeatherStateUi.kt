package com.sanaa.myweather.presentation.viewModel.mapper

import com.sanaa.myweather.R
import com.sanaa.myweather.domain.model.DailyWeatherForecast
import com.sanaa.myweather.domain.model.HourlyWeatherForecast
import com.sanaa.myweather.domain.model.WeatherInfo
import com.sanaa.myweather.domain.model.WeatherUnits
import com.sanaa.myweather.presentation.viewModel.state.DailyWeatherUiState
import com.sanaa.myweather.presentation.viewModel.state.HourlyWeatherStateUi
import com.sanaa.myweather.presentation.viewModel.state.WeatherUiInfo
import com.sanaa.myweather.presentation.viewModel.state.WeatherUnitsUiState

fun mapWeatherInfoToWeatherStateUi(weatherInfo: WeatherInfo, cityName: String): WeatherUiInfo {
    return WeatherUiInfo(
        cityName = cityName,
        temperature = weatherInfo.currentTemperature.toInt().toString(),
        weatherCondition = mapWeatherCodeToWeatherCondition(weatherInfo.weatherCode),
        weatherIcon = imageTheme(weatherInfo.isDay, weatherInfo.weatherCode),
        humidity = weatherInfo.humidityPercentage.toInt().toString(),
        windSpeed = weatherInfo.windSpeed.toInt().toString(),
        apparentTemperature = weatherInfo.apparentTemperature.toInt().toString(),
        uvIndex = weatherInfo.dailyWeatherForecast.first().uvIndex.toString(),
        pressure = weatherInfo.pressure.toInt().toString(),
        isDay = weatherInfo.isDay,
        rain = weatherInfo.rainChance.toInt().toString(),
        maxTemperature = weatherInfo.highTemperature.toInt().toString(),
        minTemperature = weatherInfo.lowTemperature.toInt().toString(),
        hourlyWeather = mapHourlyWeatherForecastToHourlyWeatherStateUi(weatherInfo.hourlyWeatherForecast),
        dailyWeatherUiState = mapDailyWeatherForecastToDailyWeatherUiState(weatherInfo.dailyWeatherForecast),
        weatherUnitsUiState = mapWeatherUnitToWeatherUnitsUiState(weatherInfo.units)
    )
}


fun mapWeatherCodeToWeatherCondition(weatherCode: Int): String {
    return when (weatherCode) {
        0 -> "Clear sky"
        1 -> "Mainly clear"
        2 -> "Partly cloudy"
        3 -> "Overcast"
        45 -> "Fog"
        48 -> "Depositing fog"
        51 -> "Drizzle"
        53 -> "Moderate drizzle"
        55 -> "Dense drizzle"
        56 -> "Freezing drizzle"
        57 -> "Dense freezing drizzle"
        61 -> "Slight Rain"
        63 -> "Moderate Rain"
        65 -> "Intensity Rain"
        71 -> "Slight Snow"
        73 -> "Moderate Snow"
        75 -> "Heavy Snow"
        77 -> "Snow grains"
        80 -> "Slight Rain Shower"
        81 -> "Moderate Rain Shower"
        82 -> "Violent Rain Shower"
        85 -> "Slight Snow Shower"
        86 -> "Heavy Snow Shower"
        95 -> "Thunderstorm"
        96 -> "Slight Hail Thunderstorm"
        99 -> "Heavy Hail Thunderstorm"
        else -> "Unknown"
    }
}

fun mapDailyWeatherForecastToDailyWeatherUiState(dailyWeatherForecast: List<DailyWeatherForecast>): List<DailyWeatherUiState> {
    return dailyWeatherForecast.indices.map {
        DailyWeatherUiState(
            day = dailyWeatherForecast[it].date.dayOfWeek.name.lowercase()
                .replaceFirstChar { it.uppercase() },
            weatherIcon = mapWeatherCodeToWeatherIconDay(dailyWeatherForecast[it].weatherCode),
            highTemperature = dailyWeatherForecast[it].highTemperature.toInt().toString(),
            lowTemperature = dailyWeatherForecast[it].lowTemperature.toInt().toString()
        )
    }
}

fun mapHourlyWeatherForecastToHourlyWeatherStateUi(hourlyWeatherForecast: List<HourlyWeatherForecast>): List<HourlyWeatherStateUi> {
    return hourlyWeatherForecast.indices.map {
        HourlyWeatherStateUi(
            temperature = hourlyWeatherForecast[it].temperature.toInt().toString(),
            weatherIcon = imageTheme(
                hourlyWeatherForecast[it].isDay,
                hourlyWeatherForecast[it].weatherCode
            ),
            time = hourlyWeatherForecast[it].time.hour.toString(),
            isDay = hourlyWeatherForecast[it].isDay
        )
    }
}


fun mapWeatherUnitToWeatherUnitsUiState(weatherInfo: WeatherUnits): WeatherUnitsUiState {
    return WeatherUnitsUiState(
        temperature = weatherInfo.temperatureUnit,
        windSpeed = weatherInfo.windSpeedUnit,
        humidity = weatherInfo.humidityPercentageUnit,
        pressure = weatherInfo.pressureUnit,
        apparentTemperature = weatherInfo.apparentTemperatureUnit,
        rain = weatherInfo.rainChanceUnit
    )
}


fun imageTheme(isDay: Boolean, weatherCode: Int): Int {
    return if (isDay) {
        mapWeatherCodeToWeatherIconDay(weatherCode)

    } else {
        mapWeatherCodeToWeatherIconNight(weatherCode)
    }
}


fun mapWeatherCodeToWeatherIconDay(weatherCode: Int): Int {
    return when (weatherCode) {
        0 -> R.drawable.clear_sky_day
        1 -> R.drawable.mainly_clear_day
        2 -> R.drawable.partily_cloudy_day
        3 -> R.drawable.overcast_day
        45 -> R.drawable.fog_day
        48 -> R.drawable.depositing_rime_fog_day
        51 -> R.drawable.drizzle_light_day
        53 -> R.drawable.drizzle_moderate_day
        55 -> R.drawable.drizzle_intensity_day
        56 -> R.drawable.freezing_drizzle_light_day
        57 -> R.drawable.freezing_drizzle_intensity_day
        61 -> R.drawable.rain_slight_day
        63 -> R.drawable.rain_moderate_day
        65 -> R.drawable.rain_intensity_day
        71 -> R.drawable.snow_fall_light
        73 -> R.drawable.snow_fall_moderate_day
        77 -> R.drawable.snow_grains_day
        80 -> R.drawable.rain_shower_slight_day
        81 -> R.drawable.rain_shower_moderate_day
        82 -> R.drawable.rain_shower_violent_day
        85 -> R.drawable.snow_shower_slight_day
        86 -> R.drawable.snow_shower_heavy_day
        96 -> R.drawable.thunderstorem_with_slight_hail_day
        99 -> R.drawable.thunderstrom_with_heavy_hail_day
        else -> R.drawable.clear_sky_day
    }
}

fun mapWeatherCodeToWeatherIconNight(weatherCode: Int): Int {
    return when (weatherCode) {
        0 -> R.drawable.clear_sky_night
        1 -> R.drawable.mainly_clear_night
        2 -> R.drawable.partly_cloudy_night
        3 -> R.drawable.overcast_night
        45 -> R.drawable.fog_night
        48 -> R.drawable.deposting_rime_fog_night
        51 -> R.drawable.drizzle_light_night
        53 -> R.drawable.drizzle_moderate_night
        55 -> R.drawable.drizzle_intensity_night
        56 -> R.drawable.freezing_drizzle_light_night
        57 -> R.drawable.freezing_drizzle_intensity_night
        61 -> R.drawable.rain_light_night
        63 -> R.drawable.rain_moderate_night
        65 -> R.drawable.rain_heavy_night
        71 -> R.drawable.snow_fall_light_night
        73 -> R.drawable.snow_fall_moderate_night
        77 -> R.drawable.snow_grains_night
        80 -> R.drawable.rain_shower_light_night
        81 -> R.drawable.rain_shower_moderate_night
        82 -> R.drawable.rain_shower_violent_night
        85 -> R.drawable.snow_shower_slight_night
        86 -> R.drawable.snow_shower_heavy_night
        96 -> R.drawable.thunderstorm_with_slight_hail_night
        99 -> R.drawable.thunderstrom_with_heavy_hail_night
        else -> R.drawable.clear_sky_night
    }
}
