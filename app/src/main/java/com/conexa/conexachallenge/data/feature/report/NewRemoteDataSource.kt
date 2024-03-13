package com.conexa.conexachallenge.data.feature.report


import com.conexa.conexachallenge.data.api.BaseRemoteDataSource
import com.conexa.conexachallenge.data.api.model.response.News
import com.conexa.conexachallenge.data.service.ApiClient
import com.google.gson.Gson
import javax.inject.Inject
import com.conexa.conexachallenge.domain.model.ResultNews


open class NewRemoteDataSource @Inject constructor(
    private val apiClient: ApiClient,
    gson: Gson,
) : BaseRemoteDataSource(gson) {

    internal suspend fun getUserProfile(): ResultNews<List<News>> {
        return getResponse(
            request = {
                apiClient.getNews()
            },
        )
    }
}
