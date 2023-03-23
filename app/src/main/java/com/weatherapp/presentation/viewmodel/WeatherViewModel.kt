package com.weatherapp.presentation.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bindingmvvm.utility.Resource
import com.weatherapp.data.pref.PrefsHelper
import com.weatherapp.domain.location.LocationTracker
import com.weatherapp.domain.model.WeatherDataMapper
import com.weatherapp.domain.usecase.UseCaseWeather
import com.weatherapp.presentation.ui.component.SearchState
import com.weatherapp.presentation.ui.component.WeatherState
import com.weatherapp.utility.api_service.Connectivity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val usWeather: UseCaseWeather,
    private val locationTracker: LocationTracker,
    private val prefsHelper: PrefsHelper
) : ViewModel(),IWeatherViewModelContract {

    var state by mutableStateOf(WeatherState())

    /**
     * fetch weather card data
     */
    override fun onGetWeatherCardData() {
        /*coroutine launch*/
        viewModelScope.launch {

            locationTracker.getCurrentLocation()?.let { location ->
                state = state.copy(
                    isLoading = true
                )
                val lastCity = prefsHelper.getLastCity()

                when(val result = usWeather.useCaseWeatherCard(location.latitude, location.longitude,lastCity)) {

                    is Resource.Loading -> {
                        state = state.copy(
                            isLoading = true,
                            onComplete = false
                        )
                    }
                    is Resource.Success -> {
                        delay(2000)
                        state = state.copy(
                            weatherInfo = result.data?.let {
                                IWeatherDataBuilder.createDataBuilder(
                                    it
                                )
                            },
                            isLoading = false,
                            onComplete = true,
                            error = null,
                        )

                        fetchWeatherForecastData(state.weatherInfo?.lat?:0.0, state.weatherInfo?.lon?:0.0)
                    }
                    is Resource.Error -> {
                        state = state.copy(
                            isLoading = false,
                            error = result.message,
                        )
                    }

                }
            } ?: kotlin.run {
                state = state.copy(
                    isLoading = false,
                    error = "Couldn't retrieve location. Make sure to grant permission and enable GPS."
                )
            }

        }
    }

    /**
     * fetch hourly weather forecast
     */

    private fun fetchWeatherForecastData(latitude: Double, longitude: Double) {
        viewModelScope.launch {
            val result =  usWeather.useCaseWeatherForcast(latitude,longitude)
            state = state.copy(
                weatherForcast = result.data
            )
        }
    }

    /**
     * search by search input query
     */

    override fun searchByCity(city:String){
        viewModelScope.launch {
            state = state.copy(
                isLoading = true
            )

            when( val result = usWeather.useCaseWeatherCard(0.0, 0.0,city)) {
                is Resource.Success -> {
                    state = state.copy(
                        weatherInfo = result.data?.let {
                            IWeatherDataBuilder.createDataBuilder(it)

                        },
                        isLoading = false,
                        onComplete = true,
                        error = null,
                    )

                    prefsHelper.saveLastCity(state.weatherInfo?.city?:"")
                    fetchWeatherForecastData(state.weatherInfo?.lat?:0.0, state.weatherInfo?.lon?:0.0)
                }
                is Resource.Error -> {
                    state = state.copy(
                        isLoading = false,
                        error = result.message,
                    )
                }

            }

        }
    }


    /**
     * Notify that the user when typing the search input
     */
    fun onSearchInputChanged(searchInput: String) {
        state = state.copy(
            searchState = SearchState.Changing(query = searchInput)
        )
    }



}