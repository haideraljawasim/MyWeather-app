package com.sanaa.myweather.data.remote.helpers

fun openMeteUrlBuilder(lat: Double, long: Double): String {
    return "https://api.open-meteo.com/v1/forecast" +
            "?latitude=$lat" +
            "&longitude=$long" +
            "&daily=weather_code,temperature_2m_min,temperature_2m_max,uv_index_max" +
            "&hourly=temperature_2m,weather_code,is_day" +
            "&current=is_day,apparent_temperature,temperature_2m,rain,precipitation_probability," +
            "relative_humidity_2m,wind_speed_10m,surface_pressure,weather_code" +
            "&forecast_days=8"
}

