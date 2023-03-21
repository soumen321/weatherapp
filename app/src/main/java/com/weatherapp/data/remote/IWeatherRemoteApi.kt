package com.weatherapp.data.remote

import retrofit2.http.GET
import retrofit2.http.Query
import com.google.gson.JsonElement
import retrofit2.Response

interface IWeatherRemoteApi {
    @GET("weather?")
    suspend fun getWeatherData(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
        @Query("appid") appid: String
    ): Response<JsonElement>

}