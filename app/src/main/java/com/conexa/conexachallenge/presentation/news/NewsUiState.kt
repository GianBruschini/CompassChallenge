package com.conexa.conexachallenge.presentation.news

import com.conexa.conexachallenge.data.api.model.response.News
import com.conexa.conexachallenge.util.BindingString

data class NewsUiState(
    val news: List<News> = emptyList(),
    val loading: Boolean = false,
    val userMessage: String? = null,
    val errorMessage: BindingString? = null,
)