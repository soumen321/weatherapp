package com.weatherapp.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.weatherapp.domain.usecase.UseCase
import com.weatherapp.presentation.ui.component.WeatherState
import kotlinx.coroutines.flow.StateFlow

abstract class BaseViewModel(
    private vararg val useCases: UseCase<*, *>?
) : ViewModel() {

    abstract val state: StateFlow<WeatherState>

    override fun onCleared() {
        useCases.forEach { it?.onCleared() }
        super.onCleared()
    }
}
