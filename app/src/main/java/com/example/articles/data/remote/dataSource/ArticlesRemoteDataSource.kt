package com.example.articles.data.remote.dataSource

import com.example.articles.data.model.BaseResponse
import com.example.articles.data.remote.webservice.WebService
import com.example.articles.utils.ResultWrapper
import com.example.articles.utils.safeApiCall
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

class ArticlesRemoteDataSource @Inject constructor(
    private val webService: WebService
) {
    suspend fun getArticles(country:String,categoryId : String) : ResultWrapper<BaseResponse> = safeApiCall(Dispatchers.IO){
        webService.getArticles(country = country,category = categoryId)
    }
}