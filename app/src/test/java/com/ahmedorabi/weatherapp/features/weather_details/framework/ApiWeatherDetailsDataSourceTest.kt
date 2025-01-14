package com.ahmedorabi.weatherapp.features.weather_details.framework

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.ahmedorabi.weatherapp.TestCoroutineRule
import com.example.core.api.ApiService
import com.example.core.api.Resource
import com.example.core.db.WeatherDao
import com.example.core.domain.forecast.ForecastResponse
import com.example.core.domain.model.WeatherResponse
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.drop
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.BDDMockito
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import java.io.IOException

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class ApiWeatherDetailsDataSourceTest {
    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()


    @Mock
    lateinit var apiService: ApiService

    @Mock
    lateinit var weatherDao: WeatherDao


    private lateinit var apiCitiesListDataSource: ApiWeatherDetailsDataSource


    @Before
    fun setup() {

        apiCitiesListDataSource = ApiWeatherDetailsDataSource(apiService, weatherDao)

    }


    @Test
    fun shouldGetWeatherDetailsSuccessResponse() {


        val weatherResponse = Mockito.mock(WeatherResponse::class.java)
        val result1 = Resource.success(weatherResponse)

        runBlocking {

            Mockito.doReturn(weatherResponse)
                .`when`(apiService)
                .getWeatherResponseAsync("london")

            val response = apiCitiesListDataSource.getWeatherDetailsResponse("london").drop(2).first()

            Assert.assertEquals(response.status, result1.status)

        }
    }


    @Test
    fun shouldGetWeatherDetailsFailureResponse() {

        val result1 = Resource.error<WeatherResponse>("NetworkError")


        runBlocking {

            BDDMockito.given(apiService.getWeatherResponseAsync("london")).willAnswer {
                throw IOException("Ooops")
            }

            val response = apiCitiesListDataSource.getWeatherDetailsResponse("london").drop(2).first()

            Assert.assertEquals(response, result1)


        }
    }



    @Test
    fun shouldGetForecastCitiesSuccessResponse() {


        val weatherResponse = Mockito.mock(ForecastResponse::class.java)
        val result1 = Resource.success(weatherResponse)

        runBlocking {

            Mockito.doReturn(weatherResponse)
                .`when`(apiService)
                .getForecastWeatherResponseAsync("london")

            val response = apiCitiesListDataSource.getForecastWeatherResponse("london").drop(1).first()

            Assert.assertEquals(response.status, result1.status)

        }
    }


    @Test
    fun shouldGetForecastCitiesFailureResponse() {

        val result1 = Resource.error<ForecastResponse>("NetworkError")


        runBlocking {

            BDDMockito.given(apiService.getForecastWeatherResponseAsync("london")).willAnswer {
                throw IOException("Ooops")
            }

            val response = apiCitiesListDataSource.getForecastWeatherResponse("london").drop(2).first()

            Assert.assertEquals(response, result1)


        }
    }
}