package com.example.newsapp.ui.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.newsapp.ui.models.Article
import retrofit2.http.DELETE

@Dao
interface ArticleDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    //suspend
    fun upsert(article: Article): Long
    @Query("SELECT * FROM articles")
    fun getAllArticles(): LiveData<List<Article>>

    @DELETE
    //suspend
    fun deleteArticle(article: Article)

}