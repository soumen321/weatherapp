package com.weatherapp.data.repository
import com.weatherapp.data.remote.IWeatherRemoteApi
import com.weatherapp.domain.model.WeatherData
import com.weatherapp.domain.model.WeatherForcast
import com.weatherapp.domain.repository.IWeatherRepository
import retrofit2.Response

class WeatherRepositoryImpl(private val api : IWeatherRemoteApi): IWeatherRepository {
    override suspend fun getWeather(lat:Double,lan:Double,appid:String) = api.getWeatherData(lat,lan,"",appid)
    override suspend fun getWeatherByCity(searchQuery:String,appid:String) = api.getWeatherByCity(searchQuery,"",appid)
    override suspend fun getWeatherForecast(
        lat: Double,
        lan: Double,
        appid: String
    ) = api.getWeatherForcast(lat,lan,"",5,appid)

}
