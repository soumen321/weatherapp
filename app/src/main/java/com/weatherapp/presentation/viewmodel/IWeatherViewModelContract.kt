package com.weatherapp.presentation.viewmodel

import androidx.lifecycle.LiveData
import com.bindingmvvm.utility.Resource
import com.weatherapp.domain.model.WeatherDataMapper

interface IWeatherViewModelContract {
    fun onGetWeatherCardData()
}