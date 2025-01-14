package com.ahmedorabi.weatherapp.features.weather_details.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.api.Resource
import com.example.core.domain.forecast.ForecastResponse
import com.example.core.domain.model.WeatherResponse
import com.example.core.domain.usecases.GetWeatherDetailsUseCase
import com.example.core.domain.usecases.GetWeatherForecastUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class WeatherDetailsViewModel @Inject constructor(
    private val getWeatherDetailsUseCase: GetWeatherDetailsUseCase,
    private val getWeatherForecastUseCase: GetWeatherForecastUseCase
) :
    ViewModel() {

    private val _citiesResponse = MutableLiveData<Resource<WeatherResponse?>>()
    val citiesResponse: LiveData<Resource<WeatherResponse?>>
        get() = _citiesResponse


    private val _forecastState = MutableStateFlow(ForecastResponse())
    val forecastState: StateFlow<ForecastResponse> = _forecastState


    fun getWeatherDetailsFlow(name: String) {
        viewModelScope.launch {
            getWeatherDetailsUseCase.invoke(name)
                .collect { response ->
                    Timber.e(response.toString())
                    _citiesResponse.value = response
                }
        }

    }

     fun getForecastResponseFlow(name: String) {
        viewModelScope.launch {
            getWeatherForecastUseCase.invoke(name)
                .collect { response ->
                    Timber.e(response.toString())
                    response.data?.let {
                        _forecastState.value = it
                    }

                }
        }

    }

    // Handle intents to update the state
    fun handleIntent(intent: GetForecastIntent) {
        when (intent) {
            is GetForecastIntent.GetForecastList -> {
                getForecastResponseFlow(intent.cityName)
            }
        }
    }
}