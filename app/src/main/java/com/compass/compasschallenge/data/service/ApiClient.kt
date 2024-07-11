package com.compass.compasschallenge.data.service




import retrofit2.Response
import retrofit2.http.GET

interface ApiClient {


    @GET("about/")
    suspend fun getAboutPage(): Response<String>





}