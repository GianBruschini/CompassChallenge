package com.conexa.conexachallenge.presentation.user

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.conexa.conexachallenge.data.api.model.response.posts.NewsResponse
import com.conexa.conexachallenge.data.api.model.response.users.UserResponse
import com.conexa.conexachallenge.domain.model.ResultNews
import com.conexa.conexachallenge.domain.usecase.report.GetNewsByIdUseCase
import com.conexa.conexachallenge.domain.usecase.report.GetNewsUseCase
import com.conexa.conexachallenge.domain.usecase.users.GetUsersUseCase
import com.conexa.conexachallenge.presentation.news.NewsUiState
import com.conexa.conexachallenge.util.errorMessage
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UsersViewModel @Inject constructor(
    private val getUsersUseCase: GetUsersUseCase
) : ViewModel() {
    private val _users = MutableStateFlow<List<UserResponse>>(emptyList())
    val users: StateFlow<List<UserResponse>> get() = _users
    private var fetchJob: Job? = null
    private val _uiState = MutableStateFlow(UsersUiState())
    val uiState: StateFlow<UsersUiState> = _uiState.asStateFlow()

    fun fetchUsers() {
        setLoading(true)
        fetchJob?.cancel()
        fetchJob = viewModelScope.launch {
            try {
                val result = getUsersUseCase()
                when (result) {
                    is ResultNews.Success -> {
                        _uiState.update { it.copy(users = result.data) }
                    }
                    is ResultNews.Error -> {
                        _uiState.value = UsersUiState(errorMessage = result.exception.errorMessage())
                    }
                }
            } catch (e: Exception) {
                _uiState.value = UsersUiState(userMessage = e.message)
            } finally {
                setLoading(false)
            }
        }
    }

    private fun setLoading(loading: Boolean) {
        _uiState.update {
            it.copy(loading = loading)
        }
    }

}

