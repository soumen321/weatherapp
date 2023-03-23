package com.weatherapp.domain.model
import com.google.gson.annotations.SerializedName

data class Cloud(
    @SerializedName("all") val all: Int
)
