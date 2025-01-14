package com.example.core.api

import com.example.core.domain.forecast.ForecastResponse
import com.example.core.domain.model.WeatherResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {


    @GET("weather?appid=d353a3f581eb873f163a28415b36ad40")
    suspend fun getWeatherResponseAsync(@Query("q") q: String): WeatherResponse

    @GET("forecast/daily?cnt=7&appid=d353a3f581eb873f163a28415b36ad40")
    suspend fun getForecastWeatherResponseAsync(@Query("q") q: String): ForecastResponse

}