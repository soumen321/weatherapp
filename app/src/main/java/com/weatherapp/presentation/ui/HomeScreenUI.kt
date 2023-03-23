package com.weatherapp.presentation.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.lifecycle.viewmodel.compose.viewModel
import com.weatherapp.presentation.ui.component.SearchBox
import com.weatherapp.presentation.ui.component.WeatherCard
import com.weatherapp.presentation.ui.component.WeatherForecast
import com.weatherapp.presentation.ui.theme.DarkBlue
import com.weatherapp.presentation.ui.theme.DeepBlue
import com.weatherapp.presentation.viewmodel.WeatherViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun HomeScreenUI(navController: NavController, viewModel: WeatherViewModel = viewModel()){
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize(),


    ) {
        val requestFocus = remember { FocusRequester() }
        Column(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(50.dp))

            SearchBox(
                actionSearch = {
                    viewModel.searchByCity("")
                }
            )
            Column(
                modifier = Modifier
                    .verticalScroll(rememberScrollState())
                    .weight(1f, false)

            ){
                Box(
                    Modifier.padding(top = 16.dp),
                ) {

                    WeatherCard(
                        viewModel.state,
                        backgroundColor = DeepBlue
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))


                WeatherForecast(state = viewModel.state)
            }

        }




        //Surface(modifier = Modifier.fillMaxSize()) {
           /* Scaffold(
                scaffoldState = scaffoldState,
                topBar = {
                    HomeTopAppBar()
                },
                modifier = Modifier
                    .fillMaxSize(),
                backgroundColor =DarkBlue
            ) {

            }*/
       // }
    }
}