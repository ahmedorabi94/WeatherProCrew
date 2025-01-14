package com.ahmedorabi.weatherapp.features.weather_details.viewmodel


import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.ahmedorabi.weatherapp.TestCoroutineRule
import com.example.core.api.Resource
import com.example.core.domain.forecast.ForecastResponse
import com.example.core.domain.model.WeatherResponse
import com.example.core.domain.usecases.GetWeatherDetailsUseCase
import com.example.core.domain.usecases.GetWeatherForecastUseCase
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.mock
import org.mockito.junit.MockitoJUnitRunner


@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class WeatherDetailsViewModelTest {

    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()


    @get:Rule
    val testCoroutineRule = TestCoroutineRule()


    @Mock
    private lateinit var apiCitiesObserver: Observer<Resource<WeatherResponse?>>

    private lateinit var viewModel: WeatherDetailsViewModel


    @Mock
    private lateinit var useCase: GetWeatherDetailsUseCase

    @Mock
    private lateinit var getWeatherForecastUseCase: GetWeatherForecastUseCase


    @Before
    fun setup() {
        viewModel = WeatherDetailsViewModel(useCase, getWeatherForecastUseCase)
    }

    @Test
    fun shouldGetWeatherDetailsSuccessResponse() {

        val weatherResponse = mock(WeatherResponse::class.java)

        val result1 = Resource.success(weatherResponse)


        val flow = flow {
            emit(result1)
        }
        testCoroutineRule.runBlockingTest {

            Mockito.doReturn(flow)
                .`when`(useCase)
                .invoke("london")

            viewModel.getWeatherDetailsFlow("london")

            viewModel.citiesResponse.observeForever(apiCitiesObserver)

            Mockito.verify(useCase).invoke("london")

            Mockito.verify(apiCitiesObserver).onChanged(Resource.success(weatherResponse))

            assertEquals(viewModel.citiesResponse.value, result1)

            viewModel.citiesResponse.removeObserver(apiCitiesObserver)


        }


    }


    @Test
    fun shouldGetForeCastSuccessResponse() {

        val forecastResponse = mock(ForecastResponse::class.java)

        val result1 = Resource.success(forecastResponse)


        val flow = flow {
            emit(result1)
        }
        testCoroutineRule.runBlockingTest {

            Mockito.doReturn(flow)
                .`when`(getWeatherForecastUseCase)
                .invoke("london")

            viewModel.getForecastResponseFlow("london")

            Mockito.verify(getWeatherForecastUseCase).invoke("london")

            val emittedValue = viewModel.forecastState.first()
            assertEquals(forecastResponse, emittedValue)


        }


    }

}