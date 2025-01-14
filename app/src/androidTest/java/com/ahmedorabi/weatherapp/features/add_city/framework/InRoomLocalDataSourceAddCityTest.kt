//package com.ahmedorabi.weatherapp.features.add_city.framework
//
//import android.content.Context
//import androidx.arch.core.executor.testing.InstantTaskExecutorRule
//import androidx.room.Room
//import androidx.test.core.app.ApplicationProvider
//import androidx.test.ext.junit.runners.AndroidJUnit4
//import com.ahmedorabi.weatherapp.getOrAwaitValue
//import com.example.core.db.AppDatabase
//import com.example.core.db.WeatherDao
//import com.example.core.domain.model.City
//import com.example.core.domain.model.HistoricalModel
//import junit.framework.Assert.assertEquals
//import kotlinx.coroutines.runBlocking
//import org.junit.After
//import org.junit.Before
//import org.junit.Rule
//import org.junit.Test
//import org.junit.runner.RunWith
//import java.io.IOException
//
//@RunWith(AndroidJUnit4::class)
//class InRoomLocalDataSourceAddCityTest {
//
//    @get:Rule
//    val instantTaskExecutorRule = InstantTaskExecutorRule()
//
//    private lateinit var weatherDao: WeatherDao
//    private lateinit var db: AppDatabase
//
//    @Before
//    fun createDb() {
//        val context = ApplicationProvider.getApplicationContext<Context>()
//        db = Room.inMemoryDatabaseBuilder(
//            context, AppDatabase::class.java
//        ).build()
//        weatherDao = db.weatherDao()
//    }
//
//    @After
//    @Throws(IOException::class)
//    fun closeDb() {
//        db.close()
//    }
//
//    @Test
//    @Throws(Exception::class)
//    fun insertAndGetAllCity() {
//
//        val city = City(name = "test1")
//
//        runBlocking {
//            weatherDao.insertCity(city)
//            val list = weatherDao.getAllCities()
//            assertEquals(list.getOrAwaitValue().size, 1)
//        }
//
//    }
//
//    @Test
//    @Throws(Exception::class)
//    fun insertAndGetAllCity2() {
//        val city = City(name = "test1")
//        val city2 = City(name = "test2")
//
//        runBlocking {
//            weatherDao.insertCity(city)
//            weatherDao.insertCity(city2)
//
//        }
//        val list = weatherDao.getAllCities()
//        assertEquals(list.getOrAwaitValue().size, 2)
//    }
//
//    @Test
//    @Throws(Exception::class)
//    fun insertAndGetHistoricalModel() {
//
//        val city = HistoricalModel(name = "test3", dateTime = "", desc = "desc", temp = 20)
//        runBlocking {
//            weatherDao.insertHistoricalModel(city)
//            val item = weatherDao.getAllHistoricalModel("test3")
//            assertEquals(item.size, 1)
//        }
//
//    }
//
//}