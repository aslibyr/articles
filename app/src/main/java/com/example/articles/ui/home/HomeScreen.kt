package com.example.articles.ui.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
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
import com.example.articles.ui.TabItemModel
import com.example.articles.ui.getTabList

@Composable
fun HomeScreen(articleClicked: (ArticleUIModel) -> Unit) {


    val tabItems = getTabList()
    var tabIndex by rememberSaveable {
        mutableIntStateOf(0)
    }

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
        ScrollableTabRow(selectedTabIndex = tabIndex) {
            tabItems.forEachIndexed { index, item ->
                Tab(text = { Text(item.title) },
                    selected = tabIndex == index,
                    onClick = { tabIndex = index }
                )
            }
        }

        ArticleTab(tabId = tabItems[tabIndex].id, articleClicked = articleClicked)
    }

}

@Composable
fun ArticleTab(
    tabId: String,
    viewModel: HomeViewModel = hiltViewModel(key = tabId),
    articleClicked: (ArticleUIModel) -> Unit
) {
    val listState = rememberLazyListState()
    LaunchedEffect(key1 = tabId) {
        viewModel.getArticles(tabId)
    }
    val articles by viewModel.articles.collectAsStateWithLifecycle()
    when (articles) {
        is Default -> {}
        is Empty -> {}
        is Error -> {}
        is Loading -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        }
        is Success -> {
            val list = (articles as Success<List<ArticleUIModel>>).response.filter { it.title != "[Removed]" }
            ArticlesList(articles = list, articleClicked = articleClicked, listState)
        }
    }


}

@Composable
fun ArticlesList(
    articles: List<ArticleUIModel>,
    articleClicked: (ArticleUIModel) -> Unit,
    listState: LazyListState
) {

    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(10.dp),
        contentPadding = PaddingValues(8.dp),
        state = listState
    ) {
        items(articles) { article ->
            ArticleItem(article = article, articleClicked = { articleClicked(article) })
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
            article.author?.let { author ->
                Text(text = author)
            }
            Text(text = article.publishedAt)

        }

    }
}