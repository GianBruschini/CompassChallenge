package com.compass.compasschallenge.domain.usecase.users

import com.compass.compasschallenge.data.feature.users.ContentRepository
import com.compass.compasschallenge.domain.model.ResultNews
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetContentUseCase @Inject constructor(
    private val contentRepository: ContentRepository,
    private val defaultDispatcher: CoroutineDispatcher = Dispatchers.Default,
) {
    suspend operator fun invoke(): ResultNews<String> =
        withContext(defaultDispatcher) {
            var authResult = contentRepository.getEvery10thCharacter()
            if (authResult is ResultNews.Error) {
                return@withContext ResultNews.Error(authResult.exception)
            }
            authResult
        }


    suspend fun fetchWordCount(): ResultNews<String> =
        withContext(defaultDispatcher) {
            var wordCountResult = contentRepository.fetchWordCount()
            if (wordCountResult is ResultNews.Error) {
                return@withContext ResultNews.Error(wordCountResult.exception)
            }
            wordCountResult
        }
}