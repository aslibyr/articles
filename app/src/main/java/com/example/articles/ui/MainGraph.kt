package com.example.articles.ui

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.example.articles.ui.detail.DetailScreen
import com.example.articles.ui.home.HomeScreen
import com.example.articles.utils.ScreenRoutes

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
            HomeScreen(articleClicked = { article ->

                navigation(article)
            })
        }
        composable(route = ScreenRoutes.DETAIL_SCREEN_ROUTE, arguments = listOf(navArgument("id") {
            type = NavType.StringType
        })) {
            val id = it.arguments?.getString("id")
            id?.let {
                DetailScreen(onBackClick = {
                    navigation(ScreenRoutes.BACK_PRESSED)
                })
            }

        }
    }
}