package com.weatherapp.di

import android.app.Application
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.weatherapp.data.pref.AppPrefs
import com.weatherapp.data.pref.PrefsHelper
import com.weatherapp.data.remote.IWeatherRemoteApi
import com.weatherapp.data.repository.WeatherRepositoryImpl
import com.weatherapp.domain.repository.IWeatherRepository
import com.weatherapp.domain.usecase.UseCaseWeather
import com.weatherapp.domain.usecase.UseCaseWeatherCard
import com.weatherapp.domain.usecase.UseCaseWeatherForcast
import com.weatherapp.utility.api_service.ServiceUrl.BASE_URL
import com.weatherapp.utility.common.RetrofitContainer
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object WeatherDI {

    @Singleton
    @Provides
    fun providesRemoteWeatherApi() : IWeatherRemoteApi =
        RetrofitContainer.getRetrofitBuilder(BASE_URL)
            .build().create(IWeatherRemoteApi::class.java)

    @Provides
    @Singleton
    fun provideFusedLocationProviderClient(app: Application): FusedLocationProviderClient {
        return LocationServices.getFusedLocationProviderClient(app)
    }

    @Singleton
    @Provides
    fun providesWeatherRepository(
        api: IWeatherRemoteApi
    ) : IWeatherRepository =
        WeatherRepositoryImpl(api)

    @Singleton
    @Provides
    fun providesWeatherUseCase(repo: IWeatherRepository) = UseCaseWeather(
        UseCaseWeatherCard(repo),
        UseCaseWeatherForcast(repo)
    )

    @Provides
    @Singleton
    fun providePrefHelper(appPrefs: AppPrefs): PrefsHelper {
        return appPrefs
    }

}