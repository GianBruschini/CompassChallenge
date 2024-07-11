package com.compass.compasschallenge.presentation.content

import com.compass.compasschallenge.domain.model.ResultNews
import com.compass.compasschallenge.domain.usecase.users.GetContentUseCase
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class WebPageContentViewModelTest {

    private lateinit var viewModel: WebPageContentViewModel
    private val useCase: GetContentUseCase = mockk()

    @Before
    fun setUp() {
        viewModel = WebPageContentViewModel(useCase)
    }

    @Test
    fun `test fetchContent success`() = runTest {
        val mockResponseEvery10thChar = ResultNews.Success(" ... ... ")
        val mockResponseWordCount = ResultNews.Success("sample: 2\ncontent: 2")

        coEvery { useCase.invoke() } returns mockResponseEvery10thChar
        coEvery { useCase.fetchWordCount() } returns mockResponseWordCount

        viewModel.fetchContent()

        coVerify { useCase.invoke() }
        coVerify { useCase.fetchWordCount() }

        assertEquals(" ... ... ", viewModel.uiState.value.every10thCharacter)
        assertEquals("sample: 2\ncontent: 2", viewModel.uiState.value.wordCount)
    }

    @Test
    fun `test fetchContent failure`() = runTest {
        val mockError = ResultNews.Error(Exception("Network Error"))

        coEvery { useCase.invoke() } returns mockError
        coEvery { useCase.fetchWordCount() } returns mockError

        viewModel.fetchContent()

        coVerify { useCase.invoke() }
        coVerify { useCase.fetchWordCount() }

        assertEquals("Network Error", viewModel.uiState.value.errorMessage)
    }
}
