package com.example.core.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.core.domain.forecast.ForecastResponse
import com.example.core.domain.model.WeatherResponse

@Dao
interface WeatherDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWeather(weatherEntity: WeatherResponse)

    @Query("DELETE FROM weather WHERE name = :city")
    suspend fun deleteWeather(city: String)


    @Query("SELECT * FROM weather WHERE name = :city ")
    suspend fun getWeatherByCity(city: String): WeatherResponse?



    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertForecast(forecast: ForecastResponse)

    @Query("DELETE FROM forecast WHERE name = :city")
    suspend fun deleteForecast(city: String)


    @Query("SELECT * FROM forecast WHERE name = :city ")
    suspend fun getForecastWeatherByCity(city: String): ForecastResponse?
}