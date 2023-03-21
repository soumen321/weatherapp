package com.weatherapp.data.repository
import com.weatherapp.data.remote.IWeatherRemoteApi
import com.weatherapp.domain.repository.IWeatherRepository

class WeatherRepositoryImpl(private val api : IWeatherRemoteApi): IWeatherRepository {
    override suspend fun getWeather(lat:Double,lan:Double,appid:String) = api.getWeatherData(lat,lan,appid)
}