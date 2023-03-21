package com.weatherapp.presentation.ui

sealed class Screens(val route: String) {
    object SplashScreen: Screens("splash")
    object Home: Screens("home")
}