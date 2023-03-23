package com.weatherapp.data.remote
import retrofit2.http.GET
import retrofit2.http.Query
import com.weatherapp.domain.model.WeatherData
import com.weatherapp.domain.model.WeatherForcast
import retrofit2.Response

interface IWeatherRemoteApi {
    @GET("weather?")
    suspend fun getWeatherData(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
        @Query("units") units: String = "metric",
        @Query("appid") appid: String
    ): Response<WeatherData>

    @GET("weather?")
    suspend fun getWeatherByCity(
        @Query("q") q: String,
        @Query("units") units: String = "metric",
        @Query("appid") appid: String
    ): Response<WeatherData>


    @GET("forecast?exclude=current,minutely,hourly,alerts")
    suspend fun getWeatherForcast(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
        @Query("units") units: String = "metric",
        @Query("cnt") cnt: Int,
        @Query("appid") appid: String
    ): Response<WeatherForcast>

}