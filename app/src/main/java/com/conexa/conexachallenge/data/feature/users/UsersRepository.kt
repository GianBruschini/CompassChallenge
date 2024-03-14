package com.conexa.conexachallenge.data.feature.users

import com.conexa.conexachallenge.data.api.model.response.posts.NewsByIdResponse
import com.conexa.conexachallenge.data.api.model.response.posts.NewsResponse
import com.conexa.conexachallenge.data.api.model.response.users.UserResponse
import com.conexa.conexachallenge.data.feature.report.NewLocalDataSource
import com.conexa.conexachallenge.data.feature.report.NewRemoteDataSource
import com.conexa.conexachallenge.domain.model.ResultNews
import javax.inject.Inject

class UsersRepository @Inject constructor(
    private val userRemoteDataSource: UsersRemoteDataSource,
    private val userLocalDataSource: UsersLocalDataSource,
) {
    suspend fun getUsers(): ResultNews<List<UserResponse>> {
        val result = userRemoteDataSource.getUsers()
        if (result is ResultNews.Success) {
            userLocalDataSource.save(result.data)
        }
        return result
    }


}