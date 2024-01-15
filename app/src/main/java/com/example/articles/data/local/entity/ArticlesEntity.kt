package com.example.articles.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "articles")
data class ArticlesEntity(
    // kendimiz id ekliyoruz, detay ekranına giderken bu id üzerinden ulaşacağız article'a
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val author: String? = null,
    val title: String,
    val description: String,
    val url: String? = null,
    val urlToImage: String? = null,
    val publishedAt: String,
    val content: String,
    val sourceId: String,
    val sourceName: String,
    val category : String? = null
)

