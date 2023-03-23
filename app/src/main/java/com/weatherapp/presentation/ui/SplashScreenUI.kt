package com.weatherapp.presentation.ui
import android.Manifest
import android.util.Log
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.weatherapp.R
import com.weatherapp.presentation.ui.theme.*
import com.weatherapp.presentation.viewmodel.WeatherViewModel

@Composable
fun SplashScreenUI(viewModel: WeatherViewModel){


    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
    ) {


       Box(
            Modifier.padding(top = 150.dp),
            contentAlignment = Alignment.TopCenter
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_weather1),
                contentDescription = ""
            )
        }

        Box(contentAlignment = Alignment.BottomCenter) {
            Card(
                modifier = Modifier
                    .clip(shape = Shapes.medium)
                    .fillMaxWidth()
                    .background(DarkBlue)
                    .padding(top = 80.dp),
            ) {
                Column(
                    Modifier
                        .background(DarkBlue)
                        .padding(horizontal = 20.dp),

                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    Text(
                        text = stringResource(id = R.string.splash_heading_txt),
                        color = White,
                        fontSize = 28.sp,
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.SemiBold
                    )
                    Spacer(modifier = Modifier.height(40.dp))

                    viewModel.state.error?.let { error ->
                        Text(
                            text = error,
                            color = Color.Red,
                            textAlign = TextAlign.Center,
                        )
                    }

                    Spacer(modifier = Modifier.height(40.dp))


                    Text(
                        text = stringResource(id = R.string.get_start_txt),
                        color = White,
                        fontSize = 22.sp,
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.SemiBold
                    )

                }
            }

            if (viewModel.state.isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center),
                    color = Color.White,
                )
            }


        }


    }
}
