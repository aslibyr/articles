package com.example.articles.ui.home

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Language
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.example.articles.R
import com.example.articles.custom.bottom_sheet.BottomModalSheet
import com.example.articles.custom.buttons.ListResetButton
import com.example.articles.custom.tab.CustomTab
import com.example.articles.custom.top_bar.TopBarComponentUIModel
import com.example.articles.custom.top_bar.TopBarView
import com.example.articles.domain.BaseError
import com.example.articles.domain.Default
import com.example.articles.domain.Empty
import com.example.articles.domain.Loading
import com.example.articles.domain.Success
import com.example.articles.domain.mapper.ArticleUIModel
import com.example.articles.ui.MainActivity
import com.example.articles.utils.Constant
import com.example.articles.utils.ScreenRoutes
import com.example.articles.utils.findActivity
import com.example.articles.utils.getCountryList
import com.example.articles.utils.theme.Pink40
import com.example.articles.utils.theme.Pink80
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(articleClicked: (String) -> Unit, viewModel: HomeViewModel = hiltViewModel()) {


    val context = LocalContext.current
    LaunchedEffect(key1 = Unit) {
        (context.findActivity() as MainActivity).getSelectedCountry(Constant.SELECTED_COUNTRY)
            ?.let { selectedCountry ->
                viewModel.updateUIEvents(HomeScreenUIState.SelectedCountry(selectedCountry))

            }
    }
    val uiState by viewModel.uiStates.collectAsStateWithLifecycle()

    if (uiState.showBottomSheet) {
        BottomModalSheet(
            onDismissRequest = {
                viewModel.updateUIEvents(HomeScreenUIState.UpdateBottomSheetState(false))
            },
            items = getCountryList(),
            itemClicked = { selected ->
                viewModel.updateUIEvents(HomeScreenUIState.SelectedCountry(selected))
                (context.findActivity() as MainActivity).setSelectedCountry(
                    Constant.SELECTED_COUNTRY,
                    selected
                )
            },
            selectedCountry = uiState.selectedCountry
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TopBarView(
            model = TopBarComponentUIModel(
                title = "Haberler",
                shouldShowBackIcon = false,
                endIcon = Icons.Default.Language,
            ),
            onEndIconClick = {
                viewModel.updateUIEvents(HomeScreenUIState.UpdateBottomSheetState(true))
            }
        )

        ScrollableTabRow(selectedTabIndex = uiState.tabIndex, divider = {},
            indicator = {
                Divider(
                    modifier = Modifier
                        .tabIndicatorOffset(it[uiState.tabIndex])
                        .border(BorderStroke(2.dp, Pink40))
                )
            }) {
            uiState.tabItems.forEachIndexed { index, item ->
                CustomTab(onClick = {
                    viewModel.updateUIEvents(
                        HomeScreenUIState.UpdateTabIndex(
                            index
                        )
                    )
                }, text = item.title, modifier = Modifier)

            }
        }
        ArticleTab(
            tabId = uiState.tabItems[uiState.tabIndex].id,
            articleClicked = articleClicked,
            selectedCountry = uiState.selectedCountry
        )
    }
}

@Composable
fun ArticleTab(
    tabId: String,
    selectedCountry: String?,
    viewModel: ArticleTabViewModel = hiltViewModel(key = tabId),
    articleClicked: (String) -> Unit
) {
    LaunchedEffect(key1 = tabId, key2 = selectedCountry) {
        selectedCountry?.let { viewModel.getArticles(country = it, categoryId = tabId) }
    }

    val articles by viewModel.articles.collectAsStateWithLifecycle()
    when (articles) {
        is Default -> {}
        is Empty -> {}
        is BaseError -> {}
        is Loading -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator(color = Pink40)
            }
        }

        is Success -> {
            val list =
                (articles as Success<List<ArticleUIModel>>).response.filter { it.title != "[Removed]" }
            ArticlesList(articles = list, articleClicked = articleClicked)
        }
    }
}

@Composable
fun ArticlesList(
    articles: List<ArticleUIModel>,
    articleClicked: (String) -> Unit,
) {
    val listState = rememberLazyListState()
    val scope = rememberCoroutineScope()
    var isScrollButtonVisible by rememberSaveable {
        mutableStateOf(false)
    }
    LaunchedEffect(key1 = listState) {
        snapshotFlow { listState.layoutInfo.visibleItemsInfo.lastOrNull()?.index }
            .collectLatest { index ->
                if (index != null && isScrollButtonVisible != listState.firstVisibleItemIndex > 0) {
                    isScrollButtonVisible = listState.firstVisibleItemIndex > 0
                }
            }
    }
    Box(modifier = Modifier.fillMaxSize()) {

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(10.dp),
            contentPadding = PaddingValues(8.dp),
            state = listState
        ) {
            items(articles) { article ->
                ArticleItem(article = article, articleClicked = {
                    articleClicked(
                        ScreenRoutes.DETAIL_SCREEN_ROUTE.replace(
                            oldValue = "{id}",
                            newValue = article.id
                        )
                    )
                })
            }
        }

        if (isScrollButtonVisible) {
            ListResetButton {
                scope.launch {
                    isScrollButtonVisible = false
                    listState.animateScrollToItem(0)
                }
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
        colors = CardDefaults.cardColors(containerColor = Pink80)

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