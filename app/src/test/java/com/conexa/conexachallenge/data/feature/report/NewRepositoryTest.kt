package com.conexa.conexachallenge.data.feature.report

import org.junit.Assert.*


import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.conexa.conexachallenge.data.api.model.response.posts.NewsByIdResponse
import com.conexa.conexachallenge.data.api.model.response.posts.NewsResponse
import com.conexa.conexachallenge.domain.model.ResultNews
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify

@ExperimentalCoroutinesApi
class NewRepositoryTest {
    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    private lateinit var newsRemoteDataSource: NewRemoteDataSource
    private lateinit var newsLocalDataSource: NewLocalDataSource
    private lateinit var newRepository: NewRepository





    @Before
    fun setup() {
        newsRemoteDataSource = mockk()
        newsLocalDataSource = mockk()
        newRepository = NewRepository(newsRemoteDataSource, newsLocalDataSource)
    }

    @Test
    fun `test getNews`() = runBlockingTest {
        val newsResponseList = listOf(
            NewsResponse(
                1,
                "Title",
                "Content",
                "image_url",
                "thumbnail_url",
                "category",
                "published_at",
                "updated_at",
                123
            )
        )

        coEvery { newsRemoteDataSource.getPosts() } returns ResultNews.Success(newsResponseList)

        val result = newRepository.getNews()


        verify(newsLocalDataSource).save(newsResponseList)

        assertEquals(ResultNews.Success(newsResponseList), result)
    }


}
