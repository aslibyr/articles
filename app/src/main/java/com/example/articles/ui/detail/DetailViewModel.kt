package com.example.articles.ui.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.articles.domain.BaseUIModel
import com.example.articles.domain.Loading
import com.example.articles.domain.mapper.ArticleUIModel
import com.example.articles.domain.usecase.GetArticleDetailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val articleDetailUseCase: GetArticleDetailUseCase,
    savedStateHandle: SavedStateHandle
) :
    ViewModel() {

    private val _article = MutableStateFlow<BaseUIModel<ArticleUIModel>>(Loading())
    val article: StateFlow<BaseUIModel<ArticleUIModel>> get() = _article
    val id = checkNotNull(savedStateHandle.get<String>("id"))

    init {
        getArticleDetail(id.toInt())

    }

    private fun getArticleDetail(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            articleDetailUseCase.invoke(id).collect {
                _article.emit(it)
            }
        }
    }

}