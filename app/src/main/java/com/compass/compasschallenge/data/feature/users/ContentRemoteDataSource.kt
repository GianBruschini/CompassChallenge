package com.compass.compasschallenge.data.feature.users

import com.compass.compasschallenge.data.api.BaseRemoteDataSource
import com.compass.compasschallenge.data.service.ApiClient
import com.compass.compasschallenge.domain.model.ResultNews
import com.google.gson.Gson
import javax.inject.Inject

open class ContentRemoteDataSource @Inject constructor(
    private val apiClient: ApiClient,
    gson: Gson,
) : BaseRemoteDataSource(gson) {

    internal suspend fun getUsers(): ResultNews<String> {
        return getResponse(
            request = {
                apiClient.getAboutPage()
            },
        )
    }


}
