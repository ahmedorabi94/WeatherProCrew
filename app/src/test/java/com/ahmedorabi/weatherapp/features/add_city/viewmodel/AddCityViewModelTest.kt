package com.ahmedorabi.weatherapp.features.add_city.viewmodel




//@ExperimentalCoroutinesApi
//@RunWith(MockitoJUnitRunner::class)
//class AddCityViewModelTest {
//
//    @get:Rule
//    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()
//
//
//    @get:Rule
//    val testCoroutineRule = TestCoroutineRule()
//
//    private lateinit var viewModel: AddCityViewModel
//
//
//    @Mock
//    private lateinit var useCase: GetCitiesLocalUseCase
//
//    @Mock
//    private lateinit var addCityUseCase: AddCityUseCase
//
//    @Before
//    fun setup() {
//        viewModel = AddCityViewModel(useCase, addCityUseCase)
//    }
//
//
//    @Test
//    fun test_add_city() {
//
//        testCoroutineRule.runBlockingTest {
//
//            viewModel.addCity(name = "london")
//
//            Mockito.verify(addCityUseCase).invoke(City(name = "london"))
//
//        }
//    }
//}