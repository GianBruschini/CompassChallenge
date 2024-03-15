package com.conexa.conexachallenge.domain.usecase.report

import com.conexa.conexachallenge.data.api.model.response.posts.NewsResponse
import com.conexa.conexachallenge.data.feature.report.NewRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import com.conexa.conexachallenge.domain.model.ResultNews

class GetNewsUseCase @Inject constructor(
    private val newRepository: NewRepository,
    private val defaultDispatcher: CoroutineDispatcher = Dispatchers.Default,
) {
    suspend operator fun invoke(): ResultNews<List<NewsResponse>> =
        withContext(defaultDispatcher) {
            var authResult = newRepository.getNews()
            if (authResult is ResultNews.Error) {
               return@withContext ResultNews.Error(authResult.exception)
             }
            authResult
        }
}