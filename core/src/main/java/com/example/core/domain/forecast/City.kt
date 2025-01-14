package com.example.core.domain.forecast

import androidx.room.Embedded

data class City(
    @Embedded
    val coord: Coord,
    val country: String,
   // val id: Int,
    val name: String,
    val population: Int,
    val timezone: Int
)