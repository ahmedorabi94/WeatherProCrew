package com.ahmedorabi.weatherapp.features.weather_details.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.example.core.api.Resource
import com.example.core.domain.forecast.ForecastResponse
import com.example.core.domain.model.WeatherResponse
import com.example.weatherhelper.getWeatherIconUrl
import com.example.weatherhelper.getWeatherTempStr
import timber.log.Timber


@Composable
fun WeatherDetailsScreen(
    response: Resource<WeatherResponse?>?,
    forecastResponse: ForecastResponse,
) {

    Box(modifier = Modifier.fillMaxSize()) {

        when (response?.status) {
            Resource.Status.LOADING -> {
                CircularProgressIndicator(
                    modifier = Modifier
                        .size(60.dp)
                        .align(Alignment.Center),
                    color = Color.Black
                )
            }

            Resource.Status.SUCCESS -> {
                Timber.e(response.data.toString())

                response.data?.let { weatherRes ->
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp)
                            .verticalScroll(rememberScrollState()),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        // Title
                        Text(
                            text = weatherRes.name,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier
                                .padding(top = 28.dp)
                                .align(Alignment.CenterHorizontally)
                        )


                        val painter =
                            rememberAsyncImagePainter(model = getWeatherIconUrl(weatherRes.weather.firstOrNull()?.icon.toString()))
                        Image(
                            contentScale = ContentScale.Fit,
                            painter = painter,
                            contentDescription = "Image loaded using rememberImagePainter",
                            modifier = Modifier
                                .size(80.dp)
                        )

                        // Description
                        Row(
                            modifier = Modifier,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "Description:",
                                fontSize = 15.sp,
                                fontWeight = FontWeight.Bold
                            )
                            Spacer(modifier = Modifier.width(20.dp))
                            Text(
                                text = weatherRes.weather.firstOrNull()?.description.toString(),
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.Blue
                            )
                        }

                        // Temperature
                        Row(
                            modifier = Modifier
                                .padding(top = 10.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "Temperature:",
                                fontSize = 15.sp,
                                fontWeight = FontWeight.Bold
                            )
                            Spacer(modifier = Modifier.width(20.dp))
                            Text(
                                text = getWeatherTempStr(weatherRes.main.temp),
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.Blue
                            )
                        }

                        // Humidity
                        Row(
                            modifier = Modifier
                                .padding(top = 10.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "Humidity:",
                                fontSize = 15.sp,
                                fontWeight = FontWeight.Bold
                            )
                            Spacer(modifier = Modifier.width(20.dp))
                            Text(
                                text = weatherRes.main.humidity.toString() + " %",
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.Blue
                            )
                        }

                        // Wind Speed
                        Row(
                            modifier = Modifier
                                .padding(top = 10.dp, bottom = 10.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "Wind Speed:",
                                fontSize = 15.sp,
                                fontWeight = FontWeight.Bold
                            )
                            Spacer(modifier = Modifier.width(20.dp))
                            Text(
                                text = weatherRes.wind.speed.toString() + " km/h",
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.Blue
                            )
                        }

                        Text(
                            text = "7-day forecast",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier
                                .padding(top = 20.dp)
                                .align(Alignment.CenterHorizontally)
                        )

                        forecastResponse.list.forEach { item ->
                            DailyForecastItem(item)
                        }
                    }
                }

            }

            Resource.Status.ERROR -> {
                Timber.e(response.message.toString())
            }

            else -> {}
        }

    }
}