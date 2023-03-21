package com.weatherapp.domain.repository

import com.google.gson.JsonElement
import retrofit2.Response

interface IWeatherRepository {
    suspend fun getWeather(lat:Double,lan:Double,appid:String) : Response<JsonElement>
}