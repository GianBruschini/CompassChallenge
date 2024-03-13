package com.conexa.conexachallenge.data.feature.report


import com.conexa.conexachallenge.data.api.model.response.News
import javax.inject.Inject
import com.conexa.conexachallenge.domain.model.ResultNews

class NewRepository @Inject constructor(
    private val userRemoteDataSource: NewRemoteDataSource,
    private val newLocalDataSource: NewLocalDataSource,
) {
    suspend fun getNews(): ResultNews<List<News>> {
        val result = userRemoteDataSource.getUserProfile()
        if (result is ResultNews.Success) {
            newLocalDataSource.save(result.data)
        }
        return result
    }
}
