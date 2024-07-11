package com.compass.compasschallenge.data.feature.users

import com.compass.compasschallenge.domain.model.ResultNews
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class ContentRepositoryTest {

    private lateinit var repository: ContentRepository
    private val remoteDataSource: ContentRemoteDataSource = mockk()
    private val localDataSource: ContentLocalDataSource = mockk()

    @Before
    fun setUp() {
        repository = ContentRepository(remoteDataSource, localDataSource)
    }

    @Test
    fun `test getEvery10thCharacter success`() = runTest {
        val mockResponse = ResultNews.Success("<html>...sample content...</html>")
        coEvery { remoteDataSource.getUsers() } returns mockResponse

        val result = repository.getEvery10thCharacter()

        assert(result is ResultNews.Success)
        assertEquals(" ... ... ", (result as ResultNews.Success).data)
    }

    @Test
    fun `test fetchWordCount success`() = runTest {
        val mockResponse = ResultNews.Success("<html>sample content sample content</html>")
        coEvery { remoteDataSource.getUsers() } returns mockResponse

        val result = repository.fetchWordCount()

        assert(result is ResultNews.Success)
        assertEquals("sample: 2\ncontent: 2", (result as ResultNews.Success).data)
    }
}
