package com.example.articles.domain.mapper

import com.example.articles.data.local.entity.ArticlesEntity
import com.example.articles.data.model.ArticleResponse

fun ArticleResponse.toEntity(category : String) : ArticlesEntity{
    return ArticlesEntity(
        author = author,
        title = title.toString(),
        description = description.toString(),
        url = url,
        urlToImage = urlToImage,
        publishedAt = publishedAt.toString(),
        content = content.toString(),
        sourceId = source?.id.toString(),
        sourceName = source?.name.toString(),
        category = category
    )
}


fun ArticlesEntity.toUIModel() : ArticleUIModel{
    return ArticleUIModel(
        author = author,
        title = title,
        description = description,
        url = url,
        urlToImage = urlToImage,
        publishedAt = publishedAt,
        content = content,
        sourceId = sourceId,
        sourceName = sourceName,
        id = id.toString()
    )
}




data class ArticleUIModel(
    val id: String,
    val author: String? = null,
    val title: String,
    val description: String,
    val url: String? = null,
    val urlToImage: String? = null,
    val publishedAt: String,
    val content: String,
    val sourceId: String,
    val sourceName: String
)
