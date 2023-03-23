package com.weatherapp.presentation.viewmodel
import com.weatherapp.domain.model.*
import com.weatherapp.utility.Constants
import java.util.*
import kotlin.math.round


interface IWeatherDataBuilder {
    companion object {

        fun createDataBuilder(dataModel: WeatherData): WeatherDataMapper {

            return WeatherDataMapper(
                currentTime = (dataModel.dt * 1000L).toDateTimeString(
                    Constants.DateFormat.EEEE_dd_MMMM,
                    dataModel.timezone
                ),
                city = dataModel.name.uppercase(),
                country = dataModel.sys.country.toCountryName().uppercase(),
                currentTemp = convertTemp(dataModel.main.temp.toString()),
                minTemp = "Min : ${convertTemp(dataModel.main.tempMin.toString())}",
                maxTemp = "Max : ${convertTemp(dataModel.main.tempMax.toString())}",
                humidity = "${dataModel.main.humidity} %",
                pressure = "${dataModel.main.pressure} hpa",
                description = "${dataModel.weatherItems.first().description?.capitalize(Locale.ENGLISH)} ",
                wind = "${round(dataModel.wind.speed).toInt()} km/h",
                visibility = "${dataModel.visibility / 1000} km",
                realFeel = "${round(dataModel.main.feelsLike).toInt()}º",
                currentIcon = dataModel.weatherItems.first().icon.toString(),
                lat = dataModel.coord.lat,
                lon = dataModel.coord.long
            )

        }
    }
}