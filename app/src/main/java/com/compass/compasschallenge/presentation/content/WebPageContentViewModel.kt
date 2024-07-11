package com.compass.compasschallenge.presentation.content

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.compass.compasschallenge.domain.model.ResultNews
import com.compass.compasschallenge.domain.usecase.users.GetContentUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WebPageContentViewModel @Inject constructor(
    private val getContentUseCase: GetContentUseCase
) : ViewModel() {
    private val _uiState = MutableStateFlow(WebPageUiState())
    val uiState: StateFlow<WebPageUiState> = _uiState.asStateFlow()

    private var fetchJob: Job? = null

    fun fetchContent() {
        setLoading(true)
        fetchJob?.cancel()
        fetchJob = viewModelScope.launch {
            try {
                val every10thCharResult = async { getContentUseCase() }
                val wordCountResult = async { getContentUseCase.fetchWordCount() }

                val every10thChar = every10thCharResult.await()
                val wordCount = wordCountResult.await()

                if (every10thChar is ResultNews.Success) {
                    _uiState.update { it.copy(every10thCharacter = every10thChar.data) }
                } else if (every10thChar is ResultNews.Error) {
                    _uiState.update { it.copy(errorMessage = every10thChar.exception.message) }
                }

                if (wordCount is ResultNews.Success) {
                    _uiState.update { it.copy(wordCount = wordCount.data) }
                } else if (wordCount is ResultNews.Error) {
                    _uiState.update { it.copy(errorMessage = wordCount.exception.message) }
                }
            } catch (e: Exception) {
                _uiState.update { it.copy(errorMessage = e.message) }
            } finally {
                setLoading(false)
            }
        }
    }

    private fun setLoading(loading: Boolean) {
        _uiState.update { it.copy(loading = loading) }
    }
}
