package lk.malandev.idetnewsapp.data.remote

import lk.malandev.idetnewsapp.domain.model.ArticleDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface NewApiService {

    companion object{
        const val API_KEY = "d8676064033741cc93e3c3a6ead5c910"
    }

    @GET("v2/top-headlines")
    suspend fun getTopHeadlines(
        @Query("country") country: String = "us",
        @Query("apiKey") apiKey: String = API_KEY
    ):ArticleDto

    @GET("v2/everything")
    suspend fun searchNews(
        @Query("q") query: String,
        @Query("apiKey") apiKey: String = API_KEY
    ):ArticleDto
}