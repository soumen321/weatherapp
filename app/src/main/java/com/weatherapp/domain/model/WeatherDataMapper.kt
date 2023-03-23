package com.weatherapp.domain.model

data class WeatherDataMapper(
    val currentTime: String,
    val city: String,
    val country: String,
    val currentTemp: String,
    val minTemp: String,
    val maxTemp: String,
    val humidity: String,
    val wind: String,
    val pressure: String,
    val description: String,
    val visibility: String,
    val realFeel: String,
    val currentIcon: String,
    val lat: Double,
    val lon: Double,
)
