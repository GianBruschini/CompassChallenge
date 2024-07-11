package com.compass.compasschallenge.domain.usecase.content

import com.compass.compasschallenge.data.feature.users.ContentRepository
import com.compass.compasschallenge.domain.model.ResultNews

import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class GetContentUseCaseTest {

    private lateinit var useCase: GetContentUseCase
    private val repository: ContentRepository = mockk()

    @Before
    fun setUp() {
        useCase = GetContentUseCase(repository)
    }

    @Test
    fun `test invoke success`() = runTest {
        val mockResponse = ResultNews.Success(" ... ... ")
        coEvery { repository.getEvery10thCharacter() } returns mockResponse

        val result = useCase.invoke()

        assert(result is ResultNews.Success)
        assertEquals(" ... ... ", (result as ResultNews.Success).data)
    }

    @Test
    fun `test fetchWordCount success`() = runTest {
        val mockResponse = ResultNews.Success("sample: 2\ncontent: 2")
        coEvery { repository.fetchWordCount() } returns mockResponse

        val result = useCase.fetchWordCount()

        assert(result is ResultNews.Success)
        assertEquals("sample: 2\ncontent: 2", (result as ResultNews.Success).data)
    }
}
