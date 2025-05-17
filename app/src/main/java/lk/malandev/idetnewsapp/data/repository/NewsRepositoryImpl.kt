package lk.malandev.idetnewsapp.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import lk.malandev.idetnewsapp.data.remote.NewApiService
import lk.malandev.idetnewsapp.domain.model.Article
import lk.malandev.idetnewsapp.domain.model.ArticleDto
import lk.malandev.idetnewsapp.domain.repository.NewsRepository
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
    private val newApiService: NewApiService
):NewsRepository {
    override suspend fun getTopHeadlines(): Flow<Result<List<Article>>>  = flow{
        try{
            val result = newApiService.getTopHeadlines()
            val articles = result.articles

            emit(Result.success(articles))
        }catch (ex:Exception){
            emit(Result.failure(ex))
        }
    }

    override suspend fun searchNews(query: String): Flow<Result<List<Article>>> = flow {
        try{
            val result = newApiService.searchNews(query)
            val articles = result.articles

            emit(Result.success(articles))
        }catch (ex:Exception){
            emit(Result.failure(ex))
        }
    }
}