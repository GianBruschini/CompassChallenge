package com.compass.compasschallenge.data.feature.users

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
                    val result = content.filterIndexed { index, _ -> (index + 1) % 10 == 0 }
                    userLocalDataSource.saveEvery10thCharacter(result)
                    ResultNews.Success(result)
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

    suspend fun fetchWordCount(): ResultNews<String> {
        return if (NetworkUtils.isNetworkAvailable()) {
            try {
                val response = userRemoteDataSource.getUsers()
                if (response is ResultNews.Success) {
                    val content = response.data
                    val words = content.split("\\s+".toRegex()).map { it.lowercase() }
                    val wordCount = words.groupingBy { it }.eachCount()
                    val wordCountString = wordCount.entries.joinToString("\n") { "${it.key}: ${it.value}" }
                    userLocalDataSource.saveWordCount(wordCountString)
                    ResultNews.Success(wordCountString)
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
