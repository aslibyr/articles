package com.example.articles.ui.detail

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import com.example.articles.custom.top_bar.TopBarComponentUIModel
import com.example.articles.custom.top_bar.TopBarView
import com.example.articles.ui.home.ArticleItem
import com.example.articles.ui.home.HomeViewModel

@Composable
fun DetailScreen(viewModel: HomeViewModel, onBackClick: () -> Unit) {
    val article = viewModel.article
    Column {
        TopBarView(
            model = TopBarComponentUIModel(
                title = "Article Detail",
                shouldShowBackIcon = true
            ),
            onBackClick = onBackClick
        )
        article?.let {
            ArticleItem(article = it) {

            }
        }
    }
}