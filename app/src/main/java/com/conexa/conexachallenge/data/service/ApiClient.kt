package com.conexa.conexachallenge.data.service



import com.conexa.conexachallenge.data.api.model.response.posts.NewsByIdResponse
import com.conexa.conexachallenge.data.api.model.response.posts.NewsResponse
import com.conexa.conexachallenge.data.api.model.response.users.UserResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiClient {


    @GET("posts")
    suspend fun getNews(): Response<List<NewsResponse>>

    @GET("posts/{postId}")
    suspend fun getNewsById(@Path("postId") postId: Int): Response<NewsByIdResponse>


    @GET("users")
    suspend fun getUsers(): Response<List<UserResponse>>





}