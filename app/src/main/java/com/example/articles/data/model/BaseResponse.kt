package com.example.articles.data.model

import com.squareup.moshi.Json

data class BaseResponse(
    @Json(name = "status")
    val status : String,
    @Json(name = "totalResults")
    val totalResults : Int,
    @Json( name= "articles")
    val articles : List<ArticleResponse>
)
