package com.example.articles.ui

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.articles.ui.detail.DetailScreen
import com.example.articles.ui.detail.DetailViewModel
import com.example.articles.ui.home.HomeScreen
import com.example.articles.ui.home.HomeViewModel
import com.example.articles.utils.ScreenRoutes
import com.example.articles.utils.sharedViewModel

fun NavGraphBuilder.mainGraph(navController: NavController,detailViewModel: DetailViewModel) {
    val navigation: (route: String) -> Unit = { route ->
        if (route == ScreenRoutes.BACK_PRESSED) {
            navController.popBackStack()
        } else {
            navController.navigate(route)
        }
    }
    navigation(route = ScreenRoutes.HOST_ROUTE, startDestination = ScreenRoutes.HOME_SCREEN_ROUTE) {
        composable(route = ScreenRoutes.HOME_SCREEN_ROUTE) {
            HomeScreen(articleClicked = { article ->
                detailViewModel.article = article
                navigation(ScreenRoutes.DETAIL_SCREEN_ROUTE)
            })
        }
        composable(route = ScreenRoutes.DETAIL_SCREEN_ROUTE) {
            DetailScreen(detailViewModel, onBackClick = {
                navigation(ScreenRoutes.BACK_PRESSED)
            })
        }
    }
}