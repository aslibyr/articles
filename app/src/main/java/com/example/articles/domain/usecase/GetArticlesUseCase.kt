package com.example.articles.domain.usecase

import com.example.articles.data.remote.repository.ExampleRepository
import com.example.articles.domain.BaseUIModel
import com.example.articles.domain.Error
import com.example.articles.domain.Loading
import com.example.articles.domain.Success
import com.example.articles.domain.mapper.ArticleUIModel
import com.example.articles.domain.mapper.toUIModel
import com.example.articles.utils.ResultWrapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetArticlesUseCase @Inject constructor(private val repository: ExampleRepository) {
    operator fun invoke(categoryId : String): Flow<BaseUIModel<List<ArticleUIModel>>> {
        return flow {
            emit(
                Loading())
            emit(
                when(val response = repository.getArticles(categoryId)){
                    is ResultWrapper.GenericError -> Error(response.error.toString())
                    ResultWrapper.Loading -> Loading()
                    ResultWrapper.NetworkError -> Error("Network error")
                    is ResultWrapper.Success -> Success(response.value.articles.map { it.toUIModel() })
                }
            )
        }
    }

}