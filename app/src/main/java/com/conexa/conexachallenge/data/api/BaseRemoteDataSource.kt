package com.conexa.conexachallenge.data.api

import com.conexa.conexachallenge.data.api.model.response.NetworkErrorResponse
import com.conexa.conexachallenge.data.api.model.response.NetworkResponse
import com.conexa.conexachallenge.data.api.model.response.News
import com.google.gson.Gson
import retrofit2.Response
import com.conexa.conexachallenge.domain.model.ResultNews
import com.conexa.conexachallenge.domain.model.exception.ApiException

abstract class BaseRemoteDataSource constructor(
    private val gson: Gson,
) {
    protected suspend fun <T> getResponse(
        request: suspend () -> Response<NetworkResponse<T>>,
    ): ResultNews<T> {
        return try {
            val response = request.invoke()
            if (response.isSuccessful) {
                ResultNews.Success(response.body()!!.data)
            } else {
                val error =
                    gson.fromJson(response.errorBody()?.string(), NetworkErrorResponse::class.java)
                val exception = ApiException(error.message ?: "")
                ResultNews.Error(exception)
            }
        } catch (e: Exception) {
            ResultNews.Error(e)
        }
    }
}
