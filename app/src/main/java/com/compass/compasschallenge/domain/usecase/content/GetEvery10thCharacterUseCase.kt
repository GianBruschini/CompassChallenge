package com.compass.compasschallenge.domain.usecase.content

import com.compass.compasschallenge.data.feature.content.ContentRepository
import com.compass.compasschallenge.domain.model.ResultNews
import javax.inject.Inject

class GetEvery10thCharacterUseCase @Inject constructor(
    private val repository: ContentRepository
) {
    suspend operator fun invoke(): ResultNews<String> {
        val response = repository.getEvery10thCharacter()
        return if (response is ResultNews.Success) {
            val content = response.data
            //if the index is a multple of 10 then it is a character
            val result = content.filterIndexed { index, _ -> (index + 1) % 10 == 0 }
            ResultNews.Success(result)
        } else {
            response
        }
    }
}