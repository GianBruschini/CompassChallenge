package com.conexa.conexachallenge.data.service



import com.conexa.conexachallenge.data.api.model.response.NetworkResponse
import com.conexa.conexachallenge.data.api.model.response.News
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiClient {


    @GET("posts")
    suspend fun getNews(): Response<NetworkResponse<List<News>>>




}