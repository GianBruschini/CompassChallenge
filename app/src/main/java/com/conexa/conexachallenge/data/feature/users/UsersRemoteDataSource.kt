package com.conexa.conexachallenge.data.feature.users

import com.conexa.conexachallenge.data.api.BaseRemoteDataSource
import com.conexa.conexachallenge.data.api.model.response.users.UserResponse
import com.conexa.conexachallenge.data.service.ApiClient
import com.conexa.conexachallenge.domain.model.ResultNews
import com.google.gson.Gson
import javax.inject.Inject

open class UsersRemoteDataSource @Inject constructor(
    private val apiClient: ApiClient,
    gson: Gson,
) : BaseRemoteDataSource(gson) {

    internal suspend fun getUsers(): ResultNews<List<UserResponse>> {
        return getResponse(
            request = {
                apiClient.getUsers()
            },
        )
    }


}
