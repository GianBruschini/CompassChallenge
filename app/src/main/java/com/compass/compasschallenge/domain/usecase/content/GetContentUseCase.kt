package com.compass.compasschallenge.domain.usecase.content

import com.compass.compasschallenge.data.feature.content.ContentRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

class GetContentUseCase @Inject constructor(
    private val contentRepository: ContentRepository,
    private val defaultDispatcher: CoroutineDispatcher = Dispatchers.Default,
) {

}