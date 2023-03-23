package com.weatherapp.data.pref

interface PrefsHelper {

    fun saveLastCity(cityName: String)

    fun getLastCity(): String
}
