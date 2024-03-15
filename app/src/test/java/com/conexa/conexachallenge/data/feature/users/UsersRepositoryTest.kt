package com.conexa.conexachallenge.data.feature.users

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.conexa.conexachallenge.data.api.model.response.users.Address
import com.conexa.conexachallenge.data.api.model.response.users.Company
import com.conexa.conexachallenge.data.api.model.response.users.Geo
import com.conexa.conexachallenge.data.api.model.response.users.Login
import com.conexa.conexachallenge.data.api.model.response.users.UserResponse
import com.conexa.conexachallenge.data.feature.connectionerrors.ErrorMessages
import com.conexa.conexachallenge.domain.model.ResultNews
import io.mockk.coEvery
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule

@ExperimentalCoroutinesApi
class UsersRepositoryTest {
    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    private lateinit var userRemoteDataSource: UsersRemoteDataSource
    private lateinit var userLocalDataSource: UsersLocalDataSource
    private lateinit var userRepository: UsersRepository

    @Before
    fun setup() {
        userRemoteDataSource = mockk()
        userLocalDataSource = mockk()
        userRepository = UsersRepository(userRemoteDataSource, userLocalDataSource)
    }

    @Test
    fun `test getUsers with Success`() = runTest {
        val userResponseList = listOf(
            UserResponse(
                1,
                "John",
                "Doe",
                "john@example.com",
                "2000-01-01",
                Login("username", "password","","","",""),
                Address("street", "city", "zipcode","", Geo("","")),
                "123456789",
                "www.example.com",
                Company("name", "catchPhrase", "bs")
            )
        )
        coEvery {userRepository.getUsers() } returns ResultNews.Success(userResponseList)

        val result = userRepository.getUsers()
        verify { userLocalDataSource.save(userResponseList) }

        assertTrue(result is ResultNews.Success)
        assertEquals(userResponseList, (result as ResultNews.Success).data)
    }

    @Test
    fun `test getUsers with Network Error`() = runTest {
        coEvery { userRemoteDataSource.getUsers() } returns ResultNews.Error(Exception("Network Error"))
        val result = userRepository.getUsers()

        verify(exactly = 0) { userLocalDataSource.save(any()) }

        assertTrue(result is ResultNews.Error)
        assertEquals("Network Error", (result as ResultNews.Error).exception.message)
    }

    @Test
    fun `test getUsers with No Local Data Available`() = runTest {
        coEvery { userRemoteDataSource.getUsers() } returns ResultNews.Error(Exception("Remote Error"))
        val result = userRepository.getUsers()
        verify(exactly = 0) { userLocalDataSource.save(any()) }
        assertTrue(result is ResultNews.Error)
        assertEquals(ErrorMessages.NO_DATA_AVAILABLE, (result as ResultNews.Error).exception.message)
    }
}
