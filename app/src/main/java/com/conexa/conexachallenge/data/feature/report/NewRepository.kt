package com.conexa.conexachallenge.data.feature.report


import com.conexa.conexachallenge.data.api.model.response.posts.NewsByIdResponse
import com.conexa.conexachallenge.data.api.model.response.posts.NewsResponse
import javax.inject.Inject
import com.conexa.conexachallenge.domain.model.ResultNews

class NewRepository @Inject constructor(
    private val newsRemoteDataSource: NewRemoteDataSource,
    private val newsLocalDataSource: NewLocalDataSource,
) {
    suspend fun getNews(): ResultNews<List<NewsResponse>> {
        val result = newsRemoteDataSource.getPosts()
        if (result is ResultNews.Success) {
            newsLocalDataSource.save(result.data)
        }
        return result
    }

    suspend fun getNewsById(newsId:Int): ResultNews<NewsByIdResponse> {
        val result = newsRemoteDataSource.getNewsById(newsId)
        return result
    }
}
