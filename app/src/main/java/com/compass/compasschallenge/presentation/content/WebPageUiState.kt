package com.compass.compasschallenge.presentation.content

data class WebPageUiState(
    val every10thCharacter: String = "",
    val wordCount: String = "",
    val errorMessage: String? = null,
    val loading: Boolean = false,
    val userMessage: String? = null
)
