package com.sanaa.myweather.domain.model

open class LocationException : Exception()
class ErrorFetchingUserLocationException : LocationException()
class CityNameNotFoundException : LocationException()


open class WeatherException : Exception()
class ErrorFetchingWeatherInfoException : WeatherException()
class WeatherInfoNotFoundException : WeatherException()