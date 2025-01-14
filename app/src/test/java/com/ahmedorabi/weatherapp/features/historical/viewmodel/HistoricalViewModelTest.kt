//package com.ahmedorabi.weatherapp.features.historical.viewmodel
//
//import androidx.arch.core.executor.testing.InstantTaskExecutorRule
//import androidx.lifecycle.Observer
//import com.ahmedorabi.weatherapp.TestCoroutineRule
//import com.example.core.domain.model.HistoricalModel
//import com.example.core.domain.usecases.GetAllHistoricalUseCase
//import junit.framework.Assert
//import kotlinx.coroutines.ExperimentalCoroutinesApi
//import kotlinx.coroutines.flow.flow
//import org.junit.Before
//import org.junit.Rule
//import org.junit.Test
//import org.junit.rules.TestRule
//import org.junit.runner.RunWith
//import org.mockito.Mock
//import org.mockito.Mockito
//import org.mockito.junit.MockitoJUnitRunner
//
//@ExperimentalCoroutinesApi
//@RunWith(MockitoJUnitRunner::class)
//class HistoricalViewModelTest {
//
//
//    @get:Rule
//    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()
//
//
//    @get:Rule
//    val testCoroutineRule = TestCoroutineRule()
//
//
//    @Mock
//    private lateinit var apiCitiesObserver: Observer<List<HistoricalModel>>
//
//    private lateinit var viewModel: HistoricalViewModel
//
//
//    @Mock
//    private lateinit var useCase: GetAllHistoricalUseCase
//
//
//    @Before
//    fun setup() {
//        viewModel = HistoricalViewModel(useCase)
//    }
//
//    @Test
//    fun shouldGetCitiesListSuccessResponse() {
//
//        val list = ArrayList<HistoricalModel>()
//        val flow = flow {
//            emit(list)
//        }
//        testCoroutineRule.runBlockingTest {
//
//            Mockito.doReturn(flow)
//                .`when`(useCase)
//                .invoke("london")
//
//            viewModel.getCitiesResponseFlow("london")
//
//            viewModel.citiesResponse.observeForever(apiCitiesObserver)
//
//            Mockito.verify(useCase).invoke("london")
//
//            Mockito.verify(apiCitiesObserver).onChanged(list)
//
//            Assert.assertEquals(viewModel.citiesResponse.value, list)
//
//            viewModel.citiesResponse.removeObserver(apiCitiesObserver)
//
//
//        }
//    }
//
//}