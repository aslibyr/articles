package com.example.articles.domain.usecase

import com.example.articles.data.remote.repository.ArticlesRepository
import com.example.articles.domain.BaseError
import com.example.articles.domain.BaseUIModel
import com.example.articles.domain.Loading
import com.example.articles.domain.Success
import com.example.articles.domain.mapper.ArticleUIModel
import com.example.articles.domain.mapper.toUIModel
import com.example.articles.utils.ResultWrapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetArticlesUseCase @Inject constructor(private val repository: ArticlesRepository) {
    operator fun invoke(
        country: String,
        categoryId: String
    ): Flow<BaseUIModel<List<ArticleUIModel>>> {
        return flow {
            emit(
                Loading()
            )
            repository.getArticles(country, categoryId).collect { response ->
                when (response) {
                    is ResultWrapper.GenericError -> {
                        emit(BaseError(response.error.toString()))
                    }

                    ResultWrapper.Loading -> {}
                    is ResultWrapper.NetworkError -> {
                        emit(BaseError("Network Error"))
                    }

                    is ResultWrapper.Success -> emit(Success(response.value.map { article ->
                        article.toUIModel()
                    }))
                }
            }
        }
    }

}