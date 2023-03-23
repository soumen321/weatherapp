package com.weatherapp.presentation.ui.component

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.weatherapp.presentation.ui.HomeScreenUI
import com.weatherapp.presentation.ui.Screens
import com.weatherapp.presentation.ui.SplashScreenUI
import com.weatherapp.presentation.viewmodel.WeatherViewModel

@Composable
fun NavGraph (
    navController: NavHostController,
    viewModel: WeatherViewModel,
    startDestination: String
){
    NavHost(
        navController = navController,
        startDestination = startDestination)
    {
        composable(route = Screens.SplashScreen.route){
            SplashScreenUI(viewModel)
        }
        composable(route = Screens.Home.route){
            HomeScreenUI(viewModel)
        }
    }
}