package com.conexa.conexachallenge.domain.usecase.users

import com.conexa.conexachallenge.data.api.model.response.posts.NewsResponse
import com.conexa.conexachallenge.data.api.model.response.users.UserResponse
import com.conexa.conexachallenge.data.feature.report.NewRepository
import com.conexa.conexachallenge.data.feature.users.UsersRepository
import com.conexa.conexachallenge.domain.model.ResultNews
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetUsersUseCase @Inject constructor(
    private val usersRepository: UsersRepository,
    private val defaultDispatcher: CoroutineDispatcher = Dispatchers.Default,
) {
    suspend operator fun invoke(): ResultNews<List<UserResponse>> =
        withContext(defaultDispatcher) {
            var authResult = usersRepository.getUsers()
            if (authResult is ResultNews.Error) {
                return@withContext ResultNews.Error(authResult.exception)
            }
            authResult
        }
}