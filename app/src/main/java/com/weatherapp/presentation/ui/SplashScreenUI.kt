package com.weatherapp.presentation.ui
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.weatherapp.R
import com.weatherapp.presentation.ui.theme.*
import com.weatherapp.presentation.viewmodel.WeatherViewModel

@Composable
fun SplashScreenUI(navController: NavController, viewModel: WeatherViewModel){


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
                        text = "Find your weather predictions in your City",
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


                    Button(
                        onClick = {},
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = DeepBlue,
                            contentColor = White
                        ),
                        contentPadding = PaddingValues(horizontal = 30.dp, vertical = 10.dp),
                        modifier = Modifier.clip(shape = Shapes.medium)
                    ) {
                        Text(text = "Get Start", fontSize = 18.sp, color = White)
                    }

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