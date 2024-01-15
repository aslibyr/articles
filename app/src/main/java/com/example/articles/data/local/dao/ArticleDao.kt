package com.example.articles.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.articles.data.local.entity.ArticlesEntity

@Dao
interface ArticleDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertArticleList(articleEntity: List<ArticlesEntity>)


    @Query("SELECT * FROM articles WHERE id = :id")
    fun getArticle(id: Int): ArticlesEntity

    @Query("select * from articles Where category = :category ")
    fun getArticles(category : String): List<ArticlesEntity>


    @Query("delete from articles Where category = :category")
    fun deleteArticles(category: String)
}
