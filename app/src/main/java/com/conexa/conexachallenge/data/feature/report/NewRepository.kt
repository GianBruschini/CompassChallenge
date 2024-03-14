package com.conexa.conexachallenge.data.feature.report


import com.conexa.conexachallenge.data.api.model.response.posts.NewsByIdResponse
import com.conexa.conexachallenge.data.api.model.response.posts.NewsResponse
import javax.inject.Inject
import com.conexa.conexachallenge.domain.model.ResultNews

class NewRepository @Inject constructor(
    private val userRemoteDataSource: NewRemoteDataSource,
    private val newLocalDataSource: NewLocalDataSource,
) {
    suspend fun getNews(): ResultNews<List<NewsResponse>> {
        val result = userRemoteDataSource.getPosts()
        if (result is ResultNews.Success) {
            newLocalDataSource.save(result.data)
        }
        return result
    }

    suspend fun getNewsById(newsId:Int): ResultNews<NewsByIdResponse> {
        val result = userRemoteDataSource.getNewsById(newsId)
        return result
    }
}
