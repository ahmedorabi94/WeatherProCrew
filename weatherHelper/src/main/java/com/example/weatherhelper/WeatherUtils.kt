package com.example.weatherhelper

import java.text.SimpleDateFormat
import java.util.Locale


fun getWeatherCelsius(temp : Double): Int {

    return (temp - 273.15).toInt()

}
fun getWeatherTempStr(temp : Double) : String{

    val celsius = getWeatherCelsius(temp)

   return "$celsius \u2103"
}


fun getWeatherIconUrl(icon : String) : String{

    return "https://openweathermap.org/img/w/${icon}.png"
}

fun formatDate(inputDate: String): String {
    val inputFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
    val outputFormat = SimpleDateFormat("dd.MM.yyyy - hh:mm a", Locale.getDefault())

    return try {
        val date = inputFormat.parse(inputDate) // Parse the input date string
        outputFormat.format(date!!) // Format to the desired output string
    } catch (e: Exception) {
        e.printStackTrace()
        "Invalid Date" // Return fallback in case of an error
    }
}