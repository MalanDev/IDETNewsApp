package lk.malandev.idetnewsapp.di

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import lk.malandev.idetnewsapp.data.remote.ApiInterceptor
import lk.malandev.idetnewsapp.data.remote.NewApiService
import lk.malandev.idetnewsapp.data.repository.NewsRepositoryImpl
import lk.malandev.idetnewsapp.domain.repository.NewsRepository
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideOkHttpClient():OkHttpClient{
        return OkHttpClient.Builder()
            .addInterceptor(ApiInterceptor())
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient):NewApiService{
        return  Retrofit.Builder()
            .baseUrl("https://newsapi.org/")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(NewApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideNewsRepository(newApiService: NewApiService):NewsRepository{
        return NewsRepositoryImpl(newApiService)
    }

    @Provides
    @Singleton
    fun provideFirebaseAuth():FirebaseAuth = FirebaseAuth.getInstance()


}