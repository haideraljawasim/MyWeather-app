package com.sanaa.myweather.data.repository.mappers

import com.sanaa.myweather.data.remote.WeatherApiResponse
import com.sanaa.myweather.data.remote.CurrentUnits
import com.sanaa.myweather.data.remote.DailyForecastDto
import com.sanaa.myweather.data.remote.HourlyForecastDto
import com.sanaa.myweather.domain.model.DailyWeatherForecast
import com.sanaa.myweather.domain.model.HourlyWeatherForecast
import com.sanaa.myweather.domain.model.WeatherInfo
import com.sanaa.myweather.domain.model.WeatherUnits
import kotlinx.datetime.LocalDate
import kotlinx.datetime.atTime
import java.time.LocalDateTime

fun WeatherApiResponse.toDomain(): WeatherInfo {
    return WeatherInfo(
        currentTemperature = this.current.temperature_2m.toDouble(),
        highTemperature = this.daily.temperature_2m_max.first(),
        lowTemperature = this.daily.temperature_2m_min.first(),
        windSpeed = this.current.wind_speed_10m,
        humidityPercentage = this.current.relative_humidity_2m,
        pressure = this.current.surface_pressure,
        apparentTemperature = this.current.apparent_temperature,
        rainChance = this.current.precipitation_probability,
        weatherCode = this.current.weather_code,
        hourlyWeatherForecast = this.hourly
            .toDomain()
            .filter { it.time.isAfter(LocalDateTime.now()) }
            .take(24),
        dailyWeatherForecast = this.daily.toDomain().takeLast(7),
        isDay = this.current.is_day == 1,
        units = this.current_units.toDomain()
    )
}


fun HourlyForecastDto.toDomain(): List<HourlyWeatherForecast> {
    return this.time.indices.map { it ->
        HourlyWeatherForecast(
            temperature = this.temperature_2m[it],
            time = LocalDateTime.parse(this.time[it]),
            weatherCode = this.weather_code[it],
            isDay = this.is_day[it] == 1
        )
    }
}


fun DailyForecastDto.toDomain(): List<DailyWeatherForecast> {
    return this.time.indices.map { it ->
        DailyWeatherForecast(
            highTemperature = this.temperature_2m_max[it],
            lowTemperature = this.temperature_2m_min[it],
            date = LocalDate.parse(this.time[it]).atTime(0, 0),
            weatherCode = this.weather_code[it],
            uvIndex = this.uv_index_max[it]
        )
    }
}


fun CurrentUnits.toDomain(): WeatherUnits {
    return WeatherUnits(
        temperatureUnit = this.temperature_2m,
        windSpeedUnit = this.wind_speed_10m,
        humidityPercentageUnit = this.relative_humidity_2m,
        pressureUnit = this.surface_pressure,
        apparentTemperatureUnit = this.apparent_temperature,
        rainChanceUnit = this.precipitation_probability
    )
}