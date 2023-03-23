package com.weatherapp.domain.usecase

import com.bindingmvvm.utility.Resource
import com.weatherapp.R
import com.weatherapp.domain.model.*
import com.weatherapp.domain.repository.IWeatherRepository
import com.weatherapp.utility.Constants
import javax.inject.Inject

class UseCaseWeatherForcast @Inject constructor(
    private val repository : IWeatherRepository
) {
    suspend operator fun invoke(lat:Double,lan:Double): Resource<WeatherForcast> {
        try {
            val response = repository.getWeatherForecast(lat,lan, Constants.OPEN_WEATHER_API_KEY)
            if (response.isSuccessful) {
                return Resource.Success(
                    response.body()?: WeatherForcast(emptyList())
                )
            } else {
                return Resource.Error("Oops!Something went wrong", R.string.lbl_something_wrong)
            }

        } catch (e:Exception){
            e.printStackTrace()
            return Resource.Error(e.localizedMessage.orEmpty(), R.string.lbl_something_wrong)
        }
    }
}