package com.conexa.conexachallenge.data.feature.report


import com.conexa.conexachallenge.data.api.BaseRemoteDataSource
import com.conexa.conexachallenge.data.api.model.response.posts.NewsByIdResponse
import com.conexa.conexachallenge.data.api.model.response.posts.NewsResponse
import com.conexa.conexachallenge.data.service.ApiClient
import com.google.gson.Gson
import javax.inject.Inject
import com.conexa.conexachallenge.domain.model.ResultNews


open class NewRemoteDataSource @Inject constructor(
    private val apiClient: ApiClient,
    gson: Gson,
) : BaseRemoteDataSource(gson) {

    internal suspend fun getPosts(): ResultNews<List<NewsResponse>> {
        return getResponse(
            request = {
                apiClient.getNews()
            },
        )
    }

    internal suspend fun getNewsById(newsId:Int): ResultNews<NewsByIdResponse> {
        return getResponse(
            request = {
                apiClient.getNewsById(newsId)
            },
        )
    }
}
