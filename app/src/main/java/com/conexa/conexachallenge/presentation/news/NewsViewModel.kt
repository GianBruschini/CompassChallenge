package com.conexa.conexachallenge.presentation.news

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.conexa.conexachallenge.data.api.model.response.News
import com.conexa.conexachallenge.domain.usecase.report.GetNewsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject
import com.conexa.conexachallenge.domain.model.ResultNews
import com.conexa.conexachallenge.util.errorMessage
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch


@HiltViewModel
class NewsViewModel @Inject constructor(
    private val getNewsUseCase: GetNewsUseCase
) : ViewModel() {
    private val _news = MutableStateFlow<List<News>>(emptyList())
    val news: StateFlow<List<News>> get() = _news
    private var fetchJob: Job? = null
    private val _uiState = MutableStateFlow(NewsUiState())
    val uiState: StateFlow<NewsUiState> = _uiState.asStateFlow()

    fun fetchNews() {
        setLoading(true)
        fetchJob?.cancel()
        fetchJob = viewModelScope.launch {
            try {
                val result = getNewsUseCase()
                when (result) {
                    is ResultNews.Success -> {
                        _uiState.update {
                            it.copy(news = result.data)
                        }
                    }
                    is ResultNews.Error -> {
                     _uiState.update {
                       it.copy(errorMessage = result.exception.errorMessage())
                     }
                    _uiState.value = NewsUiState(errorMessage = result.exception.errorMessage())
                      }
                }
            } catch (e: Exception) {
                _uiState.value = NewsUiState(userMessage = e.message)
            } finally {
                _uiState.value = _uiState.value.copy(loading = false)
            }
        }
    }

    private fun setLoading(loading: Boolean) {
        _uiState.update {
            it.copy(loading = loading)
        }
    }

}
