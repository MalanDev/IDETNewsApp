package lk.malandev.idetnewsapp.domain.repository

import kotlinx.coroutines.flow.Flow
import lk.malandev.idetnewsapp.domain.model.Article
import lk.malandev.idetnewsapp.domain.model.ArticleDto

interface NewsRepository {
    suspend fun getTopHeadlines(): Flow<Result<List<Article>>>
    suspend fun searchNews(query:String): Flow<Result<List<Article>>>
}