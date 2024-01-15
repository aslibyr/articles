package com.example.articles.data.remote.dataSource

import com.example.articles.data.local.AppDatabase
import com.example.articles.data.local.entity.ArticlesEntity
import javax.inject.Inject

class ArticlesLocalDataSource @Inject constructor(
    private val appDatabase: AppDatabase
) {
    fun getArticles(category : String) : List<ArticlesEntity> {
        return appDatabase.articlesDao().getArticles(category)
    }

    suspend fun insertArticleList(list : List<ArticlesEntity>) {
        appDatabase.articlesDao().insertArticleList(list)
    }
    fun deleteArticleList(category: String) {
        appDatabase.articlesDao().deleteArticles(category)
    }

    fun getArticle(id: Int) : ArticlesEntity {
        return appDatabase.articlesDao().getArticle(id)
    }
}