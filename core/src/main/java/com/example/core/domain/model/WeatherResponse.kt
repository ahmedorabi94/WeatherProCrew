package com.example.core.domain.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "weather")
data class WeatherResponse(
    @PrimaryKey(autoGenerate = true)
    val rowId:  Int = 0,
    val base: String,
    @Embedded
    val clouds: Clouds,
    val cod: Int,
    @Embedded
    val coord: Coord,
    val dt: Int,
   // val id: Int,
    @Embedded
    val main: Main,
    val name: String,
    @Embedded
    val sys: Sys,
    val timezone: Int,
    val visibility: Int,
    val weather: List<Weather>,
    @Embedded
    val wind: Wind
)

