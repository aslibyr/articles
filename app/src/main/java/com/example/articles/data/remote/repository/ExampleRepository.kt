package com.example.articles.data.remote.repository

import com.example.articles.data.model.BaseResponse
import com.example.articles.data.remote.dataSource.ExampleDataSource
import com.example.articles.utils.ResultWrapper
import javax.inject.Inject

class ExampleRepository @Inject constructor(
    private val exampleDataSource: ExampleDataSource
) {
    suspend fun getArticles(country:String,categoryId : String) : ResultWrapper<BaseResponse> = exampleDataSource.getArticles(country,categoryId)
}