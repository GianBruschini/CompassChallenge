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
class GetEvery10thCharacterUseCaseTest {

    private lateinit var getEvery10thCharacterUseCase: GetEvery10thCharacterUseCase
    private val repository: ContentRepository = mockk()

    @Before
    fun setUp() {
        getEvery10thCharacterUseCase = GetEvery10thCharacterUseCase(repository)
    }

    @Test
    fun `test invoke success`() = runTest {
        val mockResponse = ResultNews.Success("1234567890abcdefghij")
        coEvery { repository.getEvery10thCharacter() } returns mockResponse

        val result = getEvery10thCharacterUseCase.invoke()

        coVerify { repository.getEvery10thCharacter() }
        assert(result is ResultNews.Success)
        assertEquals("0aj", (result as ResultNews.Success).data)
    }

    @Test
    fun `test invoke failure`() = runTest {
        val mockError = ResultNews.Error(Exception("Network Error"))
        coEvery { repository.getEvery10thCharacter() } returns mockError

        val result = getEvery10thCharacterUseCase.invoke()

        coVerify { repository.getEvery10thCharacter() }
        assert(result is ResultNews.Error)
        assertEquals("Network Error", (result as ResultNews.Error).exception.message)
    }
}
