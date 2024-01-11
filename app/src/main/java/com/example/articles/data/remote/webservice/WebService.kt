package com.example.articles.data.remote.webservice

import com.example.articles.data.model.BaseResponse
import retrofit2.http.GET
import retrofit2.http.Query


interface WebService {
    @GET("top-headlines")
    suspend fun getArticles(
        @Query("country") country: String = "us",
        @Query("category") category: String
    ): BaseResponse

}
