package com.ahmedorabi.weatherapp.utils

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.ahmedorabi.weatherapp.features.home_screen.HomeScreen


enum class Screen {
    HomeScreen,
//    ADD_CITY_DIALOG,
//    WEATHER_DETAILS,
//    HISTORICAL_SCREEN,
}
sealed class NavigationItem(val route: String) {
    object HomeScreen : NavigationItem(Screen.HomeScreen.name)
//    object AddCityDialog : NavigationItem(Screen.ADD_CITY_DIALOG.name)
//    object WeatherDetailsScreen : NavigationItem(Screen.WEATHER_DETAILS.name)
//    object HistoricalScreen : NavigationItem(Screen.HISTORICAL_SCREEN.name)
}

@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    startDestination: String = NavigationItem.HomeScreen.route,

    ) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ) {

        composable(NavigationItem.HomeScreen.route) {
            HomeScreen()
        }
//        composable(NavigationItem.AddCityDialog.route) {
//            AddCityDialog()
//        }
//
//        composable(NavigationItem.WeatherDetailsScreen.route + "/{city}") { backStackEntry ->
//            val city = backStackEntry.arguments?.getString("city")
//            WeatherDetailsScreen(city ?: "")
//        }
//
//        composable(NavigationItem.HistoricalScreen.route + "/{city}") { backStackEntry ->
//            val city = backStackEntry.arguments?.getString("city")
//            HistoricalScreen(city ?: "")
//        }
    }
}