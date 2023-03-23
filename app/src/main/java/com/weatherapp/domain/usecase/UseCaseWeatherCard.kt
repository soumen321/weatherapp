package com.weatherapp.domain.usecase
import com.bindingmvvm.utility.Resource
import com.weatherapp.R
import com.weatherapp.domain.model.*
import com.weatherapp.domain.repository.IWeatherRepository
import com.weatherapp.utility.Constants
import javax.inject.Inject

class UseCaseWeatherCard @Inject constructor(
    private val repository : IWeatherRepository,
){
    suspend operator fun invoke(lat:Double,lan:Double,searchQuery:String): Resource<WeatherData>{
         try {
             val response  = if(searchQuery.isNotEmpty()){
                 repository.getWeatherByCity(searchQuery, Constants.OPEN_WEATHER_API_KEY)
             } else {
                 repository.getWeather(lat,lan, Constants.OPEN_WEATHER_API_KEY)
             }

            if (response.isSuccessful) {
                return Resource.Success(
                    response.body()?:WeatherData(
                        0,
                        "",
                        0,
                        Coord(0.00,0.00),
                        emptyList(),
                        "",
                        Main(0.00,0.00,0.00,0.00,0.00,0,0.00,0.00),
                        0,
                        Wind(0.00,0.00,0.00), Cloud(0),0, Sys(0,0,"",0,0), 0
                    )
                )


            } else {
                return Resource.Error("city not found", R.string.lbl_something_wrong)
            }

        } catch (e:Exception){
            e.printStackTrace()
           return Resource.Error(e.localizedMessage.orEmpty(), R.string.lbl_something_wrong)
        }
    }
}