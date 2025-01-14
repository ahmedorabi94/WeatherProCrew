package com.ahmedorabi.weatherapp.utils

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.ahmedorabi.weatherapp.features.home_screen.HomeScreen


enum class Screen {
    HomeScreen,
}
sealed class NavigationItem(val route: String) {
    object HomeScreen : NavigationItem(Screen.HomeScreen.name)
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
    }
}