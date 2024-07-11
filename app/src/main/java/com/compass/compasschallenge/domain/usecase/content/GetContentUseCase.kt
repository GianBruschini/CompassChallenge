package com.compass.compasschallenge.domain.usecase.content

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

}