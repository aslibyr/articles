package com.example.articles.ui.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.example.articles.R
import com.example.articles.custom.top_bar.TopBarComponentUIModel
import com.example.articles.custom.top_bar.TopBarView
import com.example.articles.domain.Default
import com.example.articles.domain.Empty
import com.example.articles.domain.Error
import com.example.articles.domain.Loading
import com.example.articles.domain.Success
import com.example.articles.domain.mapper.ArticleUIModel

@Composable
fun HomeScreen(viewModel: HomeViewModel, articleClicked: (ArticleUIModel) -> Unit) {

    val articles by viewModel.articles.collectAsStateWithLifecycle()
    val context = LocalContext.current
    when (articles) {
        is Default -> {}
        is Empty -> {}
        is Error -> {}
        is Loading -> {}
        is Success -> {
            val list = (articles as Success<List<ArticleUIModel>>).response
            ArticlesList(articles = list, articleClicked = articleClicked)
        }
    }

}

@Composable
fun ArticlesList(articles: List<ArticleUIModel>, articleClicked: (ArticleUIModel) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TopBarView(
            model = TopBarComponentUIModel(
                title = "Articles",
                shouldShowBackIcon = false
            )
        )
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(10.dp),
            contentPadding = PaddingValues(8.dp)
        ) {
            items(articles) {
                ArticleItem(article = it, articleClicked = { articleClicked(it) })
            }
        }
    }

}

@Composable
fun ArticleItem(article: ArticleUIModel, articleClicked: () -> Unit) {
    Card(
        modifier = Modifier.clickable { articleClicked() },
        elevation = CardDefaults.cardElevation(5.dp),
        shape = RoundedCornerShape(10.dp),

        ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.padding(8.dp)
        ) {
            article.urlToImage?.let { imageUrl ->
                AsyncImage(
                    modifier = Modifier.fillMaxWidth(),
                    model = imageUrl, contentDescription = "", placeholder = painterResource(
                        id = R.drawable.ic_launcher_foreground
                    ), error = painterResource(id = R.drawable.ic_launcher_foreground)
                )
            }
            Text(text = article.title)
            article.author?.let {
                Text(text = it)
            }
            Text(text = article.publishedAt)

        }

    }
}