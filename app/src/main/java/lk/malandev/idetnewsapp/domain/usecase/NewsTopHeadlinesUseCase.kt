package lk.malandev.idetnewsapp.domain.usecase

import kotlinx.coroutines.flow.Flow
import lk.malandev.idetnewsapp.domain.model.Article
import lk.malandev.idetnewsapp.domain.model.ArticleDto
import lk.malandev.idetnewsapp.domain.repository.NewsRepository
import javax.inject.Inject

class NewsTopHeadlinesUseCase @Inject constructor(private val newsRepository: NewsRepository) {
    suspend operator fun invoke(): Flow<Result<List<Article>>> = newsRepository.getTopHeadlines()
}