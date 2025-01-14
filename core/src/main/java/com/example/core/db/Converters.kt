package com.example.core.db

import androidx.room.TypeConverter
import com.example.core.domain.forecast.ForecastItem
import com.example.core.domain.model.Weather
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converters {

    @TypeConverter
    fun fromWeatherList(value: List<Weather>?): String {
        val gson = Gson()
        return gson.toJson(value)
    }

    @TypeConverter
    fun toWeatherList(value: String): List<Weather> {
        val listType = object : TypeToken<List<Weather>>() {}.type
        return Gson().fromJson(value, listType)
    }



    @TypeConverter
    fun fromForecastItemList(value: List<ForecastItem>?): String {
        val gson = Gson()
        return gson.toJson(value)
    }

    @TypeConverter
    fun toForecastItemList(value: String): List<ForecastItem> {
        val listType = object : TypeToken<List<ForecastItem>>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun fromWeatherForecastList(value: List<com.example.core.domain.forecast.Weather>?): String {
        val gson = Gson()
        return gson.toJson(value)
    }

    @TypeConverter
    fun toForecastWeatherList(value: String): List<com.example.core.domain.forecast.Weather> {
        val listType = object : TypeToken<List<com.example.core.domain.forecast.Weather>>() {}.type
        return Gson().fromJson(value, listType)
    }
}