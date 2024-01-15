package com.example.articles.data.remote.repository

import com.example.articles.data.local.entity.ArticlesEntity
import com.example.articles.data.remote.dataSource.ArticlesLocalDataSource
import com.example.articles.data.remote.dataSource.ArticlesRemoteDataSource
import com.example.articles.domain.mapper.toEntity
import com.example.articles.utils.ResultWrapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ArticlesRepository @Inject constructor(
    private val articlesRemoteDataSource: ArticlesRemoteDataSource,
    private val articlesLocalDataSource: ArticlesLocalDataSource
) {
    suspend fun getArticles(
        country: String,
        categoryId: String
    ): Flow<ResultWrapper<List<ArticlesEntity>>> {
        return flow {
            // burada yaptığımız remote dan çekip db'ye ekleme işlemi
            emit(ResultWrapper.Loading)

            when (val apiData = articlesRemoteDataSource.getArticles(country, categoryId)) {
                is ResultWrapper.GenericError -> {
                    emit(ResultWrapper.GenericError())
                }

                ResultWrapper.Loading -> {}
                is ResultWrapper.NetworkError -> {
                    emit(ResultWrapper.NetworkError)
                }

                is ResultWrapper.Success -> {
                    val response = apiData.value.articles
                    articlesLocalDataSource.deleteArticleList(categoryId)
                    response.map {
                        it.toEntity(
                            categoryId
                        )
                    }.let { articlesLocalDataSource.insertArticleList(it) }
                }
            }
            // offline first mantığı ile veriyi sadece db'den çekip domain katmanına gönderiyoruz.

            emit(ResultWrapper.Success(articlesLocalDataSource.getArticles(categoryId)))
        }
    }
}