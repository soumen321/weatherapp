package com.weatherapp.presentation.ui
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.weatherapp.presentation.ui.component.SearchBox
import com.weatherapp.presentation.ui.component.WeatherCard
import com.weatherapp.presentation.ui.component.WeatherForecast
import com.weatherapp.presentation.ui.theme.DeepBlue
import com.weatherapp.presentation.viewmodel.WeatherViewModel

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun HomeScreenUI(viewModel: WeatherViewModel = viewModel()){
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize(),


    ) {
        val keyboardController = LocalSoftwareKeyboardController.current
        Column(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(50.dp))

            Text(
                text = "Weather Forecast",
                color = Color.White,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp
            )
            Spacer(modifier = Modifier.height(12.dp))
            /* search box */
            SearchBox(
                keyboardController = keyboardController,
                onSearchChange = {
                  viewModel.onSearchInputChanged(it)
                },
                actionSearch = {
                    viewModel.searchByCity(viewModel.state.searchState.query)
                    keyboardController?.hide()
                }
            )
            Spacer(modifier = Modifier.height(8.dp))
            /* weather card */
            Column(
                modifier = Modifier
                    .verticalScroll(rememberScrollState())
                    .weight(1f, false),
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                Box(
                    Modifier.padding(top = 16.dp),
                ) {

                    WeatherCard(
                        viewModel.state,
                        backgroundColor = DeepBlue
                    )

                    if (viewModel.state.isLoading) {
                        CircularProgressIndicator(
                            modifier = Modifier.align(Alignment.Center),
                            color = Color.White,
                        )
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))

                viewModel.state.error?.let { error ->
                    Text(
                        text = error,
                        color = Color.Red,
                        textAlign = TextAlign.Center,
                    )
                }

                /* forecast box */
                WeatherForecast(state = viewModel.state)


            }

        }

    }
}