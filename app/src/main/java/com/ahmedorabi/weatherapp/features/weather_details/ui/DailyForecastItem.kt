package com.ahmedorabi.weatherapp.features.weather_details.ui


import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.example.core.domain.forecast.ForecastItem
import com.example.weatherhelper.getWeatherIconUrl
import com.example.weatherhelper.getWeatherTempStr
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter


@Composable
fun DailyForecastItem(forecast: ForecastItem) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        shape = MaterialTheme.shapes.medium,
        color = MaterialTheme.colorScheme.surface,
        shadowElevation = 2.dp
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = convertUnixTimestampToFormattedDate(forecast.dt.toLong()),
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
            Column(
                horizontalAlignment = Alignment.End
            ) {
                val painter =
                    rememberAsyncImagePainter(model = getWeatherIconUrl(forecast.weather.firstOrNull()?.icon.toString()))
                Image(
                    contentScale = ContentScale.Fit,
                    painter = painter,
                    contentDescription = "Image loaded using rememberImagePainter",
                    modifier = Modifier
                        .size(40.dp)
                )
                Text(
                    text = getWeatherTempStr(forecast.temp.day),
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = forecast.weather.firstOrNull()?.description.toString(),
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Light
                )
            }
        }
    }
}


@SuppressLint("NewApi")
fun convertUnixTimestampToFormattedDate(timestamp: Long): String {
    // Convert seconds to milliseconds
    val dateTime = LocalDateTime.ofInstant(Instant.ofEpochSecond(timestamp), ZoneId.systemDefault())
    val formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy - hh:mm a")
    return dateTime.format(formatter)
}