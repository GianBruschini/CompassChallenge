package com.conexa.conexachallenge.data.feature.report


import com.conexa.conexachallenge.data.api.model.response.posts.NewsByIdResponse
import com.conexa.conexachallenge.data.api.model.response.posts.NewsResponse
import javax.inject.Inject
import com.conexa.conexachallenge.domain.model.ResultNews
import com.conexa.conexachallenge.util.NetworkUtils

class NewRepository @Inject constructor(
    private val newsRemoteDataSource: NewRemoteDataSource,
    private val newsLocalDataSource: NewLocalDataSource,
) {
    suspend fun getNews(): ResultNews<List<NewsResponse>> {
        if (NetworkUtils.isNetworkAvailable()) {
            val result = newsRemoteDataSource.getPosts()
            if (result is ResultNews.Success) {
                newsLocalDataSource.save(result.data)
            }
            return result
        } else {
            val localNews = newsLocalDataSource.get()
            return if (localNews != null) {
                ResultNews.Success(localNews)
            } else {
                ResultNews.Error(Exception("No hay datos disponibles"))
            }
        }
    }

    suspend fun getNewsById(newsId:Int): ResultNews<NewsByIdResponse> {
        val result = newsRemoteDataSource.getNewsById(newsId)
        return result
    }
}
