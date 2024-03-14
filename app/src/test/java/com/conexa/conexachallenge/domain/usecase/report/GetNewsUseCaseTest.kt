package com.conexa.conexachallenge.domain.usecase.report

import org.junit.Assert.*

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.conexa.conexachallenge.data.api.model.response.posts.NewsResponse
import com.conexa.conexachallenge.data.feature.report.NewLocalDataSource
import com.conexa.conexachallenge.data.feature.report.NewRemoteDataSource
import com.conexa.conexachallenge.data.feature.report.NewRepository
import com.conexa.conexachallenge.domain.model.ResultNews

import io.mockk.coEvery
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

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    private val testDispatcher = TestCoroutineDispatcher()

    private lateinit var getNewsUseCase: GetNewsUseCase
    private lateinit var newsRemoteDataSource: NewRemoteDataSource
    private lateinit var newsLocalDataSource: NewLocalDataSource
    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)

        // Mock dependencies
        newsRemoteDataSource = mockk()
        newsLocalDataSource = mockk()
        val newsRepository = NewRepository(newsRemoteDataSource, newsLocalDataSource)
        getNewsUseCase = GetNewsUseCase(newsRepository, testDispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
    }

    @Test
    fun `testInvokeReturnsErrorWhenRepositoryFails`() {
        // Given
        val error = Throwable("Test error")
        coEvery { newsRemoteDataSource.getPosts() } returns ResultNews.Error(error)

        // When
        val result = runBlocking { getNewsUseCase() }

        // Then
        assertTrue(result is ResultNews.Error)
        assertEquals(error, (result as ResultNews.Error).exception)
    }


}
