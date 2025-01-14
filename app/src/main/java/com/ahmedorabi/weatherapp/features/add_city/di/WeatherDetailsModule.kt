package com.ahmedorabi.weatherapp.features.add_city.di


import com.ahmedorabi.weatherapp.features.weather_details.framework.ApiWeatherDetailsDataSource
import com.example.core.api.ApiService
import com.example.core.db.WeatherDao
import com.example.core.repo.WeatherDataSource
import com.example.core.repo.WeatherRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object WeatherDetailsModule {

    @Singleton
    @Provides
    fun provideInApiCitiesListDataSource(
        apiService: ApiService,
        cityDao: WeatherDao
    ): WeatherDataSource {
        return ApiWeatherDetailsDataSource(apiService, cityDao)
    }

    @Singleton
    @Provides
    fun provideCitiesRepository(citiesDataSource: WeatherDataSource): WeatherRepository {
        return WeatherRepository(citiesDataSource)
    }
}