package lk.malandev.idetnewsapp.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseUser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import lk.malandev.idetnewsapp.domain.model.Article
import lk.malandev.idetnewsapp.domain.usecase.NewsTopHeadlinesUseCase
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    private val newsTopHeadlinesUseCase: NewsTopHeadlinesUseCase
):ViewModel() {

    private val _newsState = MutableStateFlow<NewsState>(NewsState.Loading)
    val newsState: StateFlow<NewsState> = _newsState

    init {
        getTopHeadlines()
    }

    fun getTopHeadlines(){
        viewModelScope.launch {
            newsTopHeadlinesUseCase().collect{ result ->
                _newsState.value = when{
                    result.isSuccess -> NewsState.Success(result.getOrNull() ?: emptyList())
                    result.isFailure -> {
                        val error = result.exceptionOrNull()?.message?:"Unknown Error"
                        NewsState.Error(error)
                    }
                    else -> NewsState.Error("Unknown Error")
                }
            }
        }
    }
}


sealed class NewsState{
    object Loading:NewsState()
    data class Success(val articles:List<Article>):NewsState()
    data class Error(val message:String):NewsState()
}