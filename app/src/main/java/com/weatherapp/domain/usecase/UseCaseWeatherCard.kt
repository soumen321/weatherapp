package com.weatherapp.domain.usecase

import com.bindingmvvm.utility.Resource
import com.weatherapp.R
import com.weatherapp.domain.model.WeatherData
import com.weatherapp.domain.repository.IWeatherRepository
import com.weatherapp.utility.Constants
import javax.inject.Inject

class UseCaseWeatherCard @Inject constructor(
    private val repository : IWeatherRepository
){
    suspend operator fun invoke(lat:Double,lan:Double): Resource<WeatherData>{
         try {
            val response = repository.getWeather(lat,lan, Constants.OPEN_WEATHER_API_KEY)
            if (response.isSuccessful) {
                response.body()?.let {
                    return Resource.Success(
                        WeatherData(
                            "123",
                            12.0,
                            11.0,
                            12.0,
                            11.0,
                        )
                    )
                }
            } else {
                return Resource.Error("Oops!Something went wrong", R.string.lbl_something_wrong)
            }

        } catch (e:Exception){
            e.printStackTrace()
           return Resource.Error(e.localizedMessage.orEmpty(), R.string.lbl_something_wrong)
        }
        return Resource.Error("Oops!Something went wrong", R.string.lbl_something_wrong)
    }
}