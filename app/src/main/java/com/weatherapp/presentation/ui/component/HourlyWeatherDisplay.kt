package com.weatherapp.presentation.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.width
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.weatherapp.domain.model.CombineWeather
import com.weatherapp.domain.model.convertTemp
import com.weatherapp.domain.model.toDateTimeString
import com.weatherapp.utility.Constants
import com.weatherapp.utility.IconManager

@Composable
fun HourlyWeatherDisplay(
    weatherData: CombineWeather,
    modifier: Modifier = Modifier,
    textColor: Color = Color.White
) {

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = (weatherData.dt * 1000L).toDateTimeString(
                Constants.DateFormat.HH_mm_a,
                weatherData.timezone
            ),
            color = Color.LightGray
        )
        Image(
            painter = painterResource(id = IconManager().getforecastIcon(weatherData.weatherItems.first().icon)),
            contentDescription = null,
            modifier = Modifier.width(40.dp)
        )

        Text(
            text = "${convertTemp(weatherData.main.temp.toString())}°C",
            color = textColor,
            fontWeight = FontWeight.Bold
        )
    }
}