package com.compass.compasschallenge.presentation.content

import com.compass.compasschallenge.domain.model.ResultNews
import com.compass.compasschallenge.domain.usecase.content.GetContentUseCase
import com.compass.compasschallenge.domain.usecase.content.GetEvery10thCharacterUseCase
import com.compass.compasschallenge.domain.usecase.content.GetWordCountUseCase
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk

import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals


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
    private val getEvery10thCharacterUseCase: GetEvery10thCharacterUseCase = mockk()
    private val getWordCountUseCase: GetWordCountUseCase = mockk()

    @Before
    fun setUp() {
        viewModel = WebPageContentViewModel(getEvery10thCharacterUseCase, getWordCountUseCase)
    }

    @Test
    fun `test fetchContent success`() = runTest {
        val mockResponseEvery10thChar = ResultNews.Success(" ... ... ")
        val mockResponseWordCount = ResultNews.Success("sample: 2\ncontent: 2")

        coEvery { getEvery10thCharacterUseCase.invoke() } returns mockResponseEvery10thChar
        coEvery { getWordCountUseCase.invoke() } returns mockResponseWordCount

        viewModel.fetchContent()

        coVerify { getEvery10thCharacterUseCase.invoke() }
        coVerify { getWordCountUseCase.invoke() }

        assertEquals(" ... ... ", viewModel.uiState.value.every10thCharacter)
        assertEquals("sample: 2\ncontent: 2", viewModel.uiState.value.wordCount)
    }

    @Test
    fun `test fetchContent failure`() = runTest {
        val mockError = ResultNews.Error(Exception("Network Error"))

        coEvery { getEvery10thCharacterUseCase.invoke() } returns mockError
        coEvery { getWordCountUseCase.invoke() } returns mockError

        viewModel.fetchContent()

        coVerify { getEvery10thCharacterUseCase.invoke() }
        coVerify { getWordCountUseCase.invoke() }

        assertEquals("Network Error", viewModel.uiState.value.errorMessage)
    }
}
