package com.example.articles.ui

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.articles.ui.detail.DetailScreen
import com.example.articles.ui.home.HomeScreen
import com.example.articles.ui.home.HomeViewModel
import com.example.articles.utils.ScreenRoutes
import com.example.articles.utils.sharedViewModel

fun NavGraphBuilder.mainGraph(navController: NavController) {
    val navigation: (route: String) -> Unit = { route ->
        if (route == ScreenRoutes.BACK_PRESSED) {
            navController.popBackStack()
        } else {
            navController.navigate(route)
        }
    }
    navigation(route = ScreenRoutes.HOST_ROUTE, startDestination = ScreenRoutes.HOME_SCREEN_ROUTE) {
        composable(route = ScreenRoutes.HOME_SCREEN_ROUTE) {
            val viewModel = it.sharedViewModel<HomeViewModel>(navController = navController)
            HomeScreen(viewModel, articleClicked = { article ->
                viewModel.article = article
                navigation(ScreenRoutes.DETAIL_SCREEN_ROUTE)
            })
        }
        composable(route = ScreenRoutes.DETAIL_SCREEN_ROUTE) {
            val viewModel = it.sharedViewModel<HomeViewModel>(navController = navController)
            DetailScreen(viewModel, onBackClick = {
                navigation(ScreenRoutes.BACK_PRESSED)
            })
        }
    }
}