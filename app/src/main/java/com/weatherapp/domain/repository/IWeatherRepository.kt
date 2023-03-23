package com.weatherapp.domain.repository
import com.weatherapp.domain.model.WeatherData
import com.weatherapp.domain.model.WeatherForcast
import retrofit2.Response

interface IWeatherRepository {
    suspend fun getWeather(lat:Double,lan:Double,appid:String) : Response<WeatherData>
    suspend fun getWeatherForcast(lat:Double,lan:Double,appid:String) : Response<WeatherForcast>
}