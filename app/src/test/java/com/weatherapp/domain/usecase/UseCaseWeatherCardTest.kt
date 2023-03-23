package com.weatherapp.domain.usecase

import com.bindingmvvm.utility.Resource
import com.weatherapp.domain.model.*
import com.weatherapp.domain.repository.IWeatherRepository
import com.weatherapp.utility.Constants
import kotlinx.coroutines.runBlocking
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import retrofit2.Response

class UseCaseWeatherCardTest{
    @Mock
    lateinit var weatherApi: IWeatherRepository

    @Before
    fun setUp(){
        MockitoAnnotations.openMocks(this)
    }

    @Test
    fun `test WeatherCard empty` () = runBlocking{

        Mockito.`when`(weatherApi.getWeather(0.00,0.00, Constants.OPEN_WEATHER_API_KEY)).thenReturn(
            Response.error(401,"Unauthorized".toResponseBody())
        )

        val sut = UseCaseWeatherCard(weatherApi)
        val result = sut(0.00,0.00,"")

        assertEquals(true,result is Resource.Error)
        assertEquals("city not found",result.message)
    }

    @Test
    fun `test WeatherCard expected Weather Report` () = runBlocking{

        Mockito.`when`(weatherApi.getWeather(22.5697,88.3697, Constants.OPEN_WEATHER_API_KEY)).thenReturn(

            Response.success(WeatherData(
                 10,
                 "Kolkata",
                 0,
                 Coord(22.5697,88.3697),
                 emptyList(),
                 "",
                 Main(304.02,305.18,301.82,304.02,1006.0,48,1006.0,1005.0),
                 0,
                 Wind(0.00,0.00,0.00), Cloud(0),0, Sys(0,0,"",0,0), 0
             ))
        )


        val sut = UseCaseWeatherCard(weatherApi)
        val result = sut(22.5697,88.3697,"")

        assertEquals(true,result is Resource.Success)
        assertEquals("Kolkata",result.data?.name)
    }

    @Test
    fun `test Get Weather By CityName` () = runBlocking{
        Mockito.`when`(weatherApi.getWeatherByCity("Alaska", Constants.OPEN_WEATHER_API_KEY)).thenReturn(
            Response.success(WeatherData(
                10,
                "Alaska",
                0,
                Coord(22.5697,88.3697),
                emptyList(),
                "",
                Main(304.02,305.18,301.82,304.02,1006.0,48,1006.0,1005.0),
                0,
                Wind(0.00,0.00,0.00), Cloud(0),0, Sys(0,0,"",0,0), 0
            ))
        )

        val sut = UseCaseWeatherCard(weatherApi)
        val result = sut(22.5697,88.3697,"Alaska")

        assertEquals(true,result is Resource.Success)
        assertEquals("Alaska",result.data?.name)
    }

}