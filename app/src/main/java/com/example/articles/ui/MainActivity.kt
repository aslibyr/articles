package com.example.articles.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.EaseIn
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.articles.ui.detail.DetailViewModel
import com.example.articles.utils.ScreenRoutes
import com.example.articles.utils.theme.ArticlesTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            ArticlesTheme {
                val navController = rememberNavController()
                val viewModel = hiltViewModel<DetailViewModel>()
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    NavHost(
                        navController = navController,
                        startDestination = ScreenRoutes.HOST_ROUTE,
                        enterTransition = {
                            slideIntoContainer(
                                animationSpec = tween(300, easing = EaseIn),
                                towards = AnimatedContentTransitionScope.SlideDirection.Left
                            )
                        }, exitTransition = {
                            slideOutOfContainer(
                                animationSpec = tween(300, easing = EaseIn),
                                towards = AnimatedContentTransitionScope.SlideDirection.Right
                            )
                        }
                    ) {
                        mainGraph(navController, detailViewModel = viewModel)
                    }
                }
            }
        }
    }
}

fun getTabList() : List<TabItemModel> {
    return arrayListOf(
        TabItemModel(
            "Genel",
            "general"
        ),
        TabItemModel(
            "Spor",
            "sports"
        ),
        TabItemModel(
            "Teknoloji",
            "technology"
        ),
        TabItemModel(
            "Sağlık",
            "health"
        ),
        TabItemModel(
            "Eğlence",
            "entertainment"
        ),
        TabItemModel(
            "Bilim",
            "science"
        ),
        TabItemModel(
            "Ticari",
            "business"
        ),

    )
}

data class TabItemModel(
    val title : String,
    val id : String
)

