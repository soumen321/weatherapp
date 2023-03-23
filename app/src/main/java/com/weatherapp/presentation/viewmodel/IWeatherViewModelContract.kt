package com.weatherapp.presentation.viewmodel

interface IWeatherViewModelContract {
    fun onGetWeatherCardData()
    fun searchByCity(city:String)
}