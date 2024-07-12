package com.compass.compasschallenge.domain.usecase.content

import com.compass.compasschallenge.data.feature.content.ContentRepository
import com.compass.compasschallenge.domain.model.ResultNews
import javax.inject.Inject

class GetWordCountUseCase @Inject constructor(
    private val repository: ContentRepository
) {
    suspend operator fun invoke(): ResultNews<String> {
        val response = repository.getWordCount()
        return if (response is ResultNews.Success) {
            val content = response.data
            //divide the content of the list with a regular expression that match with blanks, tabs...
            val words = content.split("\\s+".toRegex()).map { it.lowercase() }
            val wordCount = words.groupingBy { it }.eachCount()
            val wordCountString = wordCount.entries.joinToString("\n") { "${it.key}: ${it.value} occurrences" }
            ResultNews.Success(wordCountString)
        } else {
            response
        }
    }
}