package com.ahmedorabi.weatherapp.features.weather_details.framework

import com.example.core.api.ApiService
import com.example.core.api.Resource
import com.example.core.api.ResultWrapper
import com.example.core.api.safeApiCall
import com.example.core.db.WeatherDao
import com.example.core.domain.forecast.ForecastResponse
import com.example.core.domain.model.WeatherResponse
import com.example.core.repo.WeatherDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import timber.log.Timber
import javax.inject.Inject


class ApiWeatherDetailsDataSource @Inject constructor(
    private val apiService: ApiService,
    private val weatherDao: WeatherDao
) :
    WeatherDataSource {


    override suspend fun getWeatherDetailsResponse(name: String): Flow<Resource<WeatherResponse?>> {

        return flow {

            emit(Resource.loading(null))
            val weather = weatherDao.getWeatherByCity(name.replaceFirstChar { it.uppercase() })
            emit(Resource.success(weather))

            val response = safeApiCall(Dispatchers.IO) {
                apiService.getWeatherResponseAsync(name)
            }


            when (response) {
                is ResultWrapper.Success -> {
                    try {
                        weatherDao.deleteWeather(name.replaceFirstChar { it.uppercase() })
                        weatherDao.insertWeather(response.value)
                        emit(Resource.success(weatherDao.getWeatherByCity(name.replaceFirstChar { it.uppercase() })))
                    } catch (e: Exception) {
                        Timber.e("exc error " + e.message)
                        emit(Resource.success(weather))
                    }
                }

                is ResultWrapper.Error -> {
                    emit(Resource.error(response.error?.message ?: "Unknown Error"))
                    emit(Resource.success(weather))
                }

                is ResultWrapper.NetworkError -> {
                    emit(Resource.error("NetworkError"))
                    emit(Resource.success(weather))
                }

                ResultWrapper.NoContentError -> {
                    emit(Resource.error("NoContentError"))
                    emit(Resource.success(weather))
                }
            }

        }.flowOn(Dispatchers.IO)
    }

    override suspend fun getForecastWeatherResponse(name: String): Flow<Resource<ForecastResponse?>> {
        return flow {

            emit(Resource.loading(null))

            val forecast = weatherDao.getForecastWeatherByCity(name.replaceFirstChar { it.uppercase() })
            emit(Resource.success(forecast))


            val response = safeApiCall(Dispatchers.IO) {
                apiService.getForecastWeatherResponseAsync(name)
            }

            when (response) {
                is ResultWrapper.Success -> {
                   // emit(Resource.success(response.value))

                    try {
                        weatherDao.deleteForecast(name.replaceFirstChar { it.uppercase() })
                        weatherDao.insertForecast(response.value)
                        emit(Resource.success(weatherDao.getForecastWeatherByCity(name.replaceFirstChar { it.uppercase() })))
                    } catch (e: Exception) {
                        Timber.e("exc error " + e.message)
                        emit(Resource.success(forecast))
                    }


                }

                is ResultWrapper.Error -> {
                    emit(Resource.error(response.error?.message ?: "Unknown Error"))
                    emit(Resource.success(forecast))
                }

                is ResultWrapper.NetworkError -> {
                    emit(Resource.error("NetworkError"))
                    emit(Resource.success(forecast))
                }

                ResultWrapper.NoContentError -> {
                    emit(Resource.error("NoContentError"))
                    emit(Resource.success(forecast))
                }
            }

        }.flowOn(Dispatchers.IO)
    }


}