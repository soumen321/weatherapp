package com.weatherapp.presentation.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.weatherapp.presentation.ui.theme.DeepBlue
import com.weatherapp.presentation.viewmodel.WeatherViewModel

@Composable
fun HomeScreenUI(navController: NavController, viewModel: WeatherViewModel){
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize(),


    ) {
        Box(
            Modifier.padding(top = 50.dp),
        ) {
            SearchBox()
        }
        Box(
            Modifier.padding(top = 20.dp),
        ) {

            WeatherCard(
                viewModel.state,
                backgroundColor = DeepBlue
            )
        }
    }
}