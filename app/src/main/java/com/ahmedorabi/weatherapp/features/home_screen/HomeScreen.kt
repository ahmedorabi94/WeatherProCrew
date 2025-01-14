package com.ahmedorabi.weatherapp.features.home_screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.ahmedorabi.weatherapp.features.add_city.ui.AddCityCard
import com.ahmedorabi.weatherapp.features.weather_details.ui.WeatherDetailsScreen
import com.ahmedorabi.weatherapp.features.weather_details.viewmodel.GetForecastIntent
import com.ahmedorabi.weatherapp.features.weather_details.viewmodel.WeatherDetailsViewModel

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: WeatherDetailsViewModel = hiltViewModel(),
) {

    val response by viewModel.citiesResponse.observeAsState()

    val forecastResponse by viewModel.forecastState.collectAsStateWithLifecycle()

    val cityNameMutable = remember { mutableStateOf("") }

    Column(
        modifier = modifier.fillMaxSize()
    ) {
        AddCityCard(cityName = cityNameMutable) { cityName ->
            viewModel.getWeatherDetailsFlow(cityName.replace(" ", ""))
            viewModel.handleIntent(GetForecastIntent.GetForecastList(cityName.replace(" ", "")))
        }

        WeatherDetailsScreen(response, forecastResponse)
    }
}
