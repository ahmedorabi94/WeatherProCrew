package com.ahmedorabi.weatherapp.features.weather_details.viewmodel

sealed class GetForecastIntent {
    data class GetForecastList(val cityName : String) : GetForecastIntent()
}