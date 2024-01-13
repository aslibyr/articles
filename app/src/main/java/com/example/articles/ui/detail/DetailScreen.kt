package com.example.articles.ui.detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Link
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.articles.R
import com.example.articles.custom.top_bar.TopBarComponentUIModel
import com.example.articles.custom.top_bar.TopBarView
import com.example.articles.domain.mapper.ArticleUIModel
import com.example.articles.utils.openChrome
import com.example.articles.utils.theme.FontType

@Composable
fun DetailScreen(viewModel: DetailViewModel, onBackClick: () -> Unit) {
    val article = viewModel.article
    val context = LocalContext.current
    Column(modifier = Modifier.fillMaxSize()) {
        TopBarView(
            model = TopBarComponentUIModel(
                title = "Article Detail",
                shouldShowBackIcon = true,
                endIcon = if (article?.url.isNullOrEmpty()) null else Icons.Filled.Link
            ),
            onBackClick = onBackClick,
            onEndIconClick = {
                article?.url?.let { url ->
                    context.openChrome(url)
                }
            }
        )
        article?.let {
            ArticleDetailUI(article = it)
        }
    }
}

@Composable
fun ArticleDetailUI(article: ArticleUIModel) {
    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier
            .padding(8.dp)
            .fillMaxSize()
    ) {
        article.urlToImage?.let { imageUrl ->
            AsyncImage(
                modifier = Modifier.fillMaxWidth(),
                model = imageUrl, contentDescription = "", placeholder = painterResource(
                    id = R.drawable.ic_launcher_foreground
                ), error = painterResource(id = R.drawable.ic_launcher_foreground)
            )
        }
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            article.author?.let {
                Text(
                    text = it,
                    style = TextStyle(
                        fontSize = 11.sp,
                        fontStyle = MaterialTheme.typography.headlineSmall.fontStyle
                    )
                )
            }
            Text(
                text = article.publishedAt,
                style = TextStyle(
                    fontSize = 11.sp,
                    fontStyle = MaterialTheme.typography.headlineSmall.fontStyle
                )
            )
        }
        Text(
            text = article.title, style = TextStyle(
                fontSize = 18.sp,
                fontFamily = FontType.workSansBold
            )
        )
        Text(text = article.content, fontFamily = FontType.workSansRegular, fontSize = 14.sp)


    }

}