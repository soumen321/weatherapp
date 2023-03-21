package com.weatherapp.presentation.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bindingmvvm.utility.Resource
import com.weatherapp.R
import com.weatherapp.domain.location.LocationTracker
import com.weatherapp.domain.model.WeatherData
import com.weatherapp.domain.usecase.UseCaseWeather
import com.weatherapp.presentation.WeatherState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val usWeather: UseCaseWeather,
    private val locationTracker: LocationTracker
) : ViewModel(),IWeatherViewModelContract {

    private val mLDWeatherCard = MutableLiveData<Resource<WeatherData>>()

    var state by mutableStateOf(WeatherState())
        private set

    override fun onGetWeatherCardData() {
       // mLDWeatherCard.postValue(Resource.Loading())
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
                            weatherInfo = result.data,
                            isLoading = false,
                            onComplete = true
                        )
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


           /* val result = useCaseWeather.useCaseWeatherCard(lat,lon) ?: return@launch
            mLDWeatherCard.postValue(result)
            val weather = result.data
            if (null != weather) {
               // mLDWeatherCard.postValue(weather)
            } else {
                mLDWeatherCard.postValue(Resource.UnknownError(R.string.lbl_something_wrong))
            }*/
        }
    }

    override fun doObserveWeatherCardData(): LiveData<Resource<WeatherData>> = mLDWeatherCard


}