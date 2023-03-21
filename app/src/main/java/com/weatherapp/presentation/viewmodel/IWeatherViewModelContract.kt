package com.weatherapp.presentation.viewmodel

import androidx.lifecycle.LiveData
import com.bindingmvvm.utility.Resource
import com.weatherapp.domain.model.WeatherData

interface IWeatherViewModelContract {

    fun onGetWeatherCardData()

    fun doObserveWeatherCardData(): LiveData<Resource<WeatherData>>
}