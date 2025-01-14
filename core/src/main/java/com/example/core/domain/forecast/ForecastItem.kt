package com.example.core.domain.forecast

import androidx.room.Embedded

data class ForecastItem(
    val clouds: Int,
    val deg: Int,
    val dt: Int,
    @Embedded
    val feels_like: FeelsLike,
    val gust: Double,
    val humidity: Int,
    val pop: Int,
    val pressure: Int,
    val speed: Double,
    val sunrise: Int,
    val sunset: Int,
    val temp: Temp,
    val weather: List<Weather>
)