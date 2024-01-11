package com.example.articles.domain.mapper

import com.example.articles.data.model.ArticleResponse

fun ArticleResponse.toUIModel() : ArticleUIModel{
    return ArticleUIModel(
        author = author.toString(),
        title = title.toString(),
        description = description.toString(),
        url = url.toString(),
        urlToImage = urlToImage,
        publishedAt = publishedAt.toString(),
        content = content.toString(),
        sourceId = source?.id.toString(),
        sourceName = source?.name.toString()
    )
}


data class ArticleUIModel(
    val author: String,
    val title: String,
    val description: String,
    val url: String,
    val urlToImage: String? = null,
    val publishedAt: String,
    val content: String,
    val sourceId: String,
    val sourceName: String


)
