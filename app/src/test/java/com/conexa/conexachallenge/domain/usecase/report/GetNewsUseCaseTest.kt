package com.conexa.conexachallenge.domain.usecase.report

import org.junit.Assert.*

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.conexa.conexachallenge.data.api.model.response.posts.NewsResponse
import com.conexa.conexachallenge.data.feature.report.NewLocalDataSource
import com.conexa.conexachallenge.data.feature.report.NewRemoteDataSource
import com.conexa.conexachallenge.data.feature.report.NewRepository
import com.conexa.conexachallenge.domain.model.ResultNews
import io.mockk.MockKAnnotations

import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4


@RunWith(JUnit4::class)
class GetNewsUseCaseTest {


    @RelaxedMockK
    @MockK
    private lateinit var newsRepository: NewRepository
    lateinit var getNewsUseCase: GetNewsUseCase


    @get:Rule
    val rule = InstantTaskExecutorRule()

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun onBefore(){
        MockKAnnotations.init(this)
        newsRepository = mockk()
        getNewsUseCase = GetNewsUseCase(newsRepository)
        Dispatchers.setMain(Dispatchers.Unconfined)

    }


    @OptIn(ExperimentalCoroutinesApi::class)
    @After
    fun tearDown() {
        Dispatchers.resetMain()

    }

    @Test
    fun testInvokeReturnsErrorWhenRepositoryFails() {
        // Given
        val error = Throwable("Test error")
        coEvery { newsRepository.getNews() } returns ResultNews.Error(error)

        // When
        val result = runBlocking { getNewsUseCase() }

        // Then
        assertTrue(result is ResultNews.Error)
        assertEquals(error, (result as ResultNews.Error).exception)
    }

    @Test
    fun testInvokeReturnsSuccessWhenRepositorySucceeds() {
        // Given
        val newsList = listOf(
            NewsResponse(
                1,
                "Test Title",
                "Test Content",
                "Test Image",
                "Test Thumbnail",
                "Test Category",
                "2024-03-15T12:00:00",
                "2024-03-15T12:00:00",
                1
            )
        )
        coEvery { newsRepository.getNews() } returns ResultNews.Success(newsList)

        // When
        val result = runBlocking { getNewsUseCase() }

        // Then
        assertTrue(result is ResultNews.Success)
        assertEquals(newsList, (result as ResultNews.Success).data)
    }


}
