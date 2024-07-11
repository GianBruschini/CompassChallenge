package com.compass.compasschallenge.domain.usecase.content

import com.compass.compasschallenge.data.feature.content.ContentRepository
import com.compass.compasschallenge.domain.model.ResultNews
import org.junit.Assert.*

import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class GetWordCountUseCaseTest {

    private lateinit var getWordCountUseCase: GetWordCountUseCase
    private val repository: ContentRepository = mockk()

    @Before
    fun setUp() {
        getWordCountUseCase = GetWordCountUseCase(repository)
    }

    @Test
    fun `test invoke success`() = runTest {
        val mockResponse = ResultNews.Success("Hello world hello world")
        coEvery { repository.getWordCount() } returns mockResponse

        val result = getWordCountUseCase.invoke()

        coVerify { repository.getWordCount() }
        assert(result is ResultNews.Success)
        assertEquals("hello: 2 occurrences\nworld: 2 occurrences", (result as ResultNews.Success).data)
    }

    @Test
    fun `test invoke failure`() = runTest {
        val mockError = ResultNews.Error(Exception("Network Error"))
        coEvery { repository.getWordCount() } returns mockError

        val result = getWordCountUseCase.invoke()

        coVerify { repository.getWordCount() }
        assert(result is ResultNews.Error)
        assertEquals("Network Error", (result as ResultNews.Error).exception.message)
    }
}
