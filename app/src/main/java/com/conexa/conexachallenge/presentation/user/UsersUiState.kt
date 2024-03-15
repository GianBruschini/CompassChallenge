package com.conexa.conexachallenge.presentation.user

import com.conexa.conexachallenge.data.api.model.response.posts.NewsByIdResponse
import com.conexa.conexachallenge.data.api.model.response.posts.NewsResponse
import com.conexa.conexachallenge.data.api.model.response.users.UserResponse
import com.conexa.conexachallenge.util.BindingString

data class UsersUiState(
    val users: List<UserResponse> = emptyList(),
    val loading: Boolean = false,
    val userMessage: String? = null,
    val errorMessage: BindingString? = null,
)