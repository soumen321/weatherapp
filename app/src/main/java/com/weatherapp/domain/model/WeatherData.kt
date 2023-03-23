package com.weatherapp.domain.model
import com.google.gson.annotations.SerializedName

data class WeatherData(
    @SerializedName("id") val id: Int = 0,
    @SerializedName("name") val name: String = "",
    @SerializedName("cod") val cod: Int = 0,
    @SerializedName("coord") val coord: Coord = Coord(0.00,0.00),
    @SerializedName("weather") val weatherItems: List<WeatherItem> = emptyList(),
    @SerializedName("base") val base: String = "",
    @SerializedName("main") val main: Main,
    @SerializedName("visibility") val visibility: Int,
    @SerializedName("wind") val wind: Wind,
    @SerializedName("clouds") val clouds: Cloud,
    @SerializedName("dt") val dt: Long,
    @SerializedName("sys") val sys: Sys,
    @SerializedName("timezone") val timezone: Int
)
