package com.weatherapp.domain.model

import com.google.gson.annotations.SerializedName

data class WeatherForcast(
    @SerializedName("list") val forcastlist: List<CombineWeather>,
)

data class CombineWeather(
    @SerializedName("weather") val weatherItems: List<WeatherItem> = emptyList(),
    @SerializedName("main") val main: Main,
    @SerializedName("dt") val dt: Long,
    @SerializedName("timezone") val timezone: Int
)
