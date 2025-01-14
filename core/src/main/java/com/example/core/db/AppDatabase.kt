package com.example.core.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.core.domain.forecast.ForecastResponse
import com.example.core.domain.model.WeatherResponse

@Database(entities = [WeatherResponse::class , ForecastResponse::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun weatherDao(): WeatherDao
}