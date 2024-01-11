package com.example.articles.ui.detail

import androidx.lifecycle.ViewModel
import com.example.articles.domain.mapper.ArticleUIModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor() : ViewModel(){

    var article: ArticleUIModel? = null
}