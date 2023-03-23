package com.weatherapp.presentation.ui.component
import com.weatherapp.domain.model.WeatherDataMapper
import com.weatherapp.domain.model.WeatherForcast

data class WeatherState(
    val weatherInfo: WeatherDataMapper? = null,
    val weatherForcast: WeatherForcast? = null,
    val isLoading: Boolean = false,
    val onComplete:Boolean = false,
    val error: String? = null,
    val searchState: SearchState = SearchState.Closed()
)
