package com.weatherapp.presentation.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
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
            SplashScreenUI(navController,viewModel)
        }
        composable(route = Screens.Home.route){
            HomeScreenUI(navController,viewModel)
        }
    }
}