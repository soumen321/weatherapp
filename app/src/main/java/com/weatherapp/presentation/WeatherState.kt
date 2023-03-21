package com.weatherapp.presentation
import com.weatherapp.domain.model.WeatherData

data class WeatherState(
    val weatherInfo: WeatherData? = null,
    val isLoading: Boolean = false,
    val onComplete:Boolean = false,
    val error: String? = null
)
