package com.compass.compasschallenge.data.feature.content

import com.compass.compasschallenge.domain.model.ResultNews
import com.compass.compasschallenge.util.NetworkUtils
import javax.inject.Inject

class ContentRepository @Inject constructor(
    private val userRemoteDataSource: ContentRemoteDataSource,
    private val userLocalDataSource: ContentLocalDataSource,
) {
    suspend fun getEvery10thCharacter(): ResultNews<String> {
        return if (NetworkUtils.isNetworkAvailable()) {
            try {
                val response = userRemoteDataSource.getUsers()
                if (response is ResultNews.Success) {
                    val content = response.data
                    userLocalDataSource.saveEvery10thCharacter(content)
                    ResultNews.Success(content)
                } else {
                    response as ResultNews.Error
                }
            } catch (e: Exception) {
                ResultNews.Error(e)
            }
        } else {
            val localResult = userLocalDataSource.getEvery10thCharacters()
            if (localResult != null) {
                ResultNews.Success(localResult)
            } else {
                ResultNews.Error(Exception("No data available"))
            }
        }
    }

    suspend fun getWordCount(): ResultNews<String> {
        return if (NetworkUtils.isNetworkAvailable()) {
            try {
                val response = userRemoteDataSource.getUsers()
                if (response is ResultNews.Success) {
                    val content = response.data
                    userLocalDataSource.saveWordCount(content)
                    ResultNews.Success(content)
                } else {
                    response as ResultNews.Error
                }
            } catch (e: Exception) {
                ResultNews.Error(e)
            }
        } else {
            val localResult = userLocalDataSource.getWordCounts()
            if (localResult != null) {
                ResultNews.Success(localResult)
            } else {
                ResultNews.Error(Exception("No data available"))
            }
        }
    }

}
