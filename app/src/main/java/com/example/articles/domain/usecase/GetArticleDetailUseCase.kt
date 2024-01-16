package com.example.articles.domain.usecase

import com.example.articles.data.remote.repository.ArticlesRepository
import com.example.articles.domain.BaseError
import com.example.articles.domain.BaseUIModel
import com.example.articles.domain.Success
import com.example.articles.domain.mapper.ArticleUIModel
import com.example.articles.domain.mapper.toUIModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetArticleDetailUseCase @Inject constructor(private val repository: ArticlesRepository) {

    operator fun invoke(id: Int): Flow<BaseUIModel<ArticleUIModel>> {
        return flow {
            try {
                emit(Success(repository.getArticle(id).toUIModel()))
            } catch (e: Exception) {
                emit(BaseError(e.message.toString()))
            }
        }
    }
}