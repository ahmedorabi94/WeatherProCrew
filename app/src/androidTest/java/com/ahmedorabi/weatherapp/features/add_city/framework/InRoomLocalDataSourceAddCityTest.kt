package com.ahmedorabi.weatherapp.features.add_city.framework

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.core.db.AppDatabase
import com.example.core.db.WeatherDao
import com.example.core.domain.forecast.ForecastResponse
import com.example.core.domain.model.Clouds
import com.example.core.domain.model.Coord
import com.example.core.domain.model.Main
import com.example.core.domain.model.Sys
import com.example.core.domain.model.WeatherResponse
import com.example.core.domain.model.Wind
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class InRoomLocalDataSourceAddCityTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var weatherDao: WeatherDao
    private lateinit var db: AppDatabase

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(
            context, AppDatabase::class.java
        ).build()
        weatherDao = db.weatherDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    @Throws(Exception::class)
    fun insertAndGetAllWeather() {

        val weather = WeatherResponse(
            name = "test1",
            base = "",
            clouds = Clouds(1),
            cod = 0,
            coord = Coord(0.0, 0.0),
            dt = 1,
            main = Main(0.0, 0, 0, 0.0, 0.0, 0.0),
            sys = Sys("", 0, 0),
            timezone = 1,
            visibility = 1,
            weather = emptyList(),
            wind = Wind(0, 0.0, 0.0),
        )

        runBlocking {
            weatherDao.insertWeather(weather)
            val list = weatherDao.getAllWeathers()
            assertEquals(list.getOrAwaitValue().size, 1)
        }

    }

    @Test
    @Throws(Exception::class)
    fun insertAndGetAllWeather2() {


        val weather = WeatherResponse(
            name = "test1",
            base = "",
            clouds = Clouds(1),
            cod = 0,
            coord = Coord(0.0, 0.0),
            dt = 1,
            main = Main(0.0, 0, 0, 0.0, 0.0, 0.0),
            sys = Sys("", 0, 0),
            timezone = 1,
            visibility = 1,
            weather = emptyList(),
            wind = Wind(0, 0.0, 0.0),
        )

        val weather2 = WeatherResponse(
            name = "test2",
            base = "",
            clouds = Clouds(1),
            cod = 0,
            coord = Coord(0.0, 0.0),
            dt = 1,
            main = Main(0.0, 0, 0, 0.0, 0.0, 0.0),
            sys = Sys("", 0, 0),
            timezone = 1,
            visibility = 1,
            weather = emptyList(),
            wind = Wind(0, 0.0, 0.0),
        )


        runBlocking {
            weatherDao.insertWeather(weather)
            weatherDao.insertWeather(weather2)

        }
        val list = weatherDao.getAllWeathers()
        assertEquals(list.getOrAwaitValue().size, 2)
    }

    @Test
    @Throws(Exception::class)
    fun insertAndGetForecastModel() {
        val forecast = ForecastResponse(
            cnt = 1,
            cod = ""
        )
        runBlocking {
            weatherDao.insertForecast(forecast)
            val item = weatherDao.getAllForecasts()

            assertEquals(item.getOrAwaitValue().size, 1)
        }

    }

}