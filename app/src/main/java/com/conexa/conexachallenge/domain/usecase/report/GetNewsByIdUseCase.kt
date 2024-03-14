package com.conexa.conexachallenge.domain.usecase.report

import com.conexa.conexachallenge.data.api.model.response.posts.NewsByIdResponse
import com.conexa.conexachallenge.data.feature.report.NewRepository
import com.conexa.conexachallenge.domain.model.ResultNews
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetNewsByIdUseCase @Inject constructor(
    private val newRepository: NewRepository,
    private val defaultDispatcher: CoroutineDispatcher = Dispatchers.Default,
) {
    suspend operator fun invoke(newsId: Int): ResultNews<NewsByIdResponse> =
        withContext(defaultDispatcher) {
            try {
                val result = newRepository.getNewsById(newsId)
                return@withContext result
            } catch (e: Exception) {
                return@withContext ResultNews.Error(e)
            }
        }
}
