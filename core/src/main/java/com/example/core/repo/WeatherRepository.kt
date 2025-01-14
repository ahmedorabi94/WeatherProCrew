package com.example.core.repo

import javax.inject.Inject

class WeatherRepository @Inject constructor(private val dataSource: WeatherDataSource) {

    suspend fun getWeatherDetailsResponse(name : String) = dataSource.getWeatherDetailsResponse(name)

    suspend fun getForecastWeatherResponse(name : String) = dataSource.getForecastWeatherResponse(name)

}