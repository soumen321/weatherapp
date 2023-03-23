package com.weatherapp.presentation.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bindingmvvm.utility.Resource
import com.weatherapp.domain.location.LocationTracker
import com.weatherapp.domain.model.WeatherDataMapper
import com.weatherapp.domain.usecase.UseCaseWeather
import com.weatherapp.presentation.ui.component.SearchState
import com.weatherapp.presentation.ui.component.WeatherState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val usWeather: UseCaseWeather,
    private val locationTracker: LocationTracker
) : ViewModel(),IWeatherViewModelContract {

    var state by mutableStateOf(WeatherState())


    override fun onGetWeatherCardData() {
        /*coroutine launch*/
        viewModelScope.launch {

            locationTracker.getCurrentLocation()?.let { location ->
                state = state.copy(
                    isLoading = true,
                    onComplete = false
                )

                when(val result = usWeather.useCaseWeatherCard(location.latitude, location.longitude)) {

                    is Resource.Loading -> {
                        state = state.copy(
                            isLoading = true,
                            onComplete = false
                        )
                    }
                    is Resource.Success -> {
                        state = state.copy(
                            weatherInfo = result.data?.let {
                                IWeatherDataBuilder.createDataBuilder(
                                    it
                                )
                            },
                            isLoading = false,
                            onComplete = true
                        )
                        fetchWeatherForcastData(location.latitude, location.longitude)
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

    private fun fetchWeatherForcastData(latitude: Double, longitude: Double) {
        viewModelScope.launch {
            val result =  usWeather.useCaseWeatherForcast(latitude,longitude)
            state = state.copy(
                weatherForcast = result.data
            )
        }
    }

    fun searchByCity(city:String){

    }

    /**
     * Notify that the user when typing the search input
     */
    fun onSearchInputChanged(searchInput: String) {
        state = state.copy(
            searchState = SearchState.Changing(query = searchInput)
        )

    }

    /**
     * Enable or disable search view
     */
    fun enableSearchView(enabled: Boolean) {
        state = state.copy(
            searchState = SearchState.Changing(query = state.searchState.query)
        )
    }

    enum class WeatherIndex(private val index: Int) {
        Today(0), Tomorrow(1);

        fun value(): Int = index

        companion object {
            fun from(index: Int): WeatherIndex {
                return if (index == 0) Today else Tomorrow
            }
        }
    }


}