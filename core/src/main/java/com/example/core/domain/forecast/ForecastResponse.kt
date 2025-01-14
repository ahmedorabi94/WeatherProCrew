package com.example.core.domain.forecast

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "forecast")
data class ForecastResponse(
    @PrimaryKey(autoGenerate = true)
    val rowId:  Int = 0,
    @Embedded
    val city: City?=null,
    val cnt: Int = 0,
    val cod: String = "",
    val list: List<ForecastItem> = emptyList(),
    val message: Double = 0.0
)

