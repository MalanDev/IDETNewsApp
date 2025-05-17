package lk.malandev.idetnewsapp.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import lk.malandev.idetnewsapp.data.repository.AuthRepositoryImpl
import lk.malandev.idetnewsapp.data.repository.NewsRepositoryImpl
import lk.malandev.idetnewsapp.domain.repository.AuthRepository
import lk.malandev.idetnewsapp.domain.repository.NewsRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindAuthRepository(authRepositoryImpl: AuthRepositoryImpl):AuthRepository

    @Binds
    @Singleton
    abstract fun bindNewsRepository(newsRepositoryImpl: NewsRepositoryImpl):NewsRepository
}