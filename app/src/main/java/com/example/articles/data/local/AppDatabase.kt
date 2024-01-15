package com.example.articles.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.articles.data.local.dao.ArticleDao
import com.example.articles.data.local.entity.ArticlesEntity

@Database(entities = [ArticlesEntity::class], version = 2)
abstract class AppDatabase : RoomDatabase() {
    abstract fun articlesDao(): ArticleDao
}
