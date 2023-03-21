package com.weatherapp.domain.model

import java.time.LocalDateTime

data class WeatherData(
    val time: String,
    val temperatureCelsius: Double,
    val pressure: Double,
    val windSpeed: Double,
    val humidity: Double,
)
