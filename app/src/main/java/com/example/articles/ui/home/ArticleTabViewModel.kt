package com.example.articles.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.articles.domain.BaseUIModel
import com.example.articles.domain.Loading
import com.example.articles.domain.mapper.ArticleUIModel
import com.example.articles.domain.usecase.GetArticlesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ArticleTabViewModel @Inject constructor(private val getArticlesUseCase: GetArticlesUseCase) :
    ViewModel() {

    private val _articles = MutableStateFlow<BaseUIModel<List<ArticleUIModel>>>(Loading())
    val articles: StateFlow<BaseUIModel<List<ArticleUIModel>>> get() = _articles


    fun getArticles(country:String,categoryId: String) {
        viewModelScope.launch {
                getArticlesUseCase.invoke(country,categoryId).collect { articles ->
                    _articles.emit(articles)
                }
        }
    }
}