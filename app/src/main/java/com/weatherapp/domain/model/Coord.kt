package com.weatherapp.domain.model

import com.google.gson.annotations.SerializedName

data class Coord(
    @SerializedName("lat") val lat: Double = 0.00,
    @SerializedName("lon") val long: Double = 0.00
)
