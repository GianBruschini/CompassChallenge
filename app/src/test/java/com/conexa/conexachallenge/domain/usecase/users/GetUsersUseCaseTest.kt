package com.conexa.conexachallenge.domain.usecase.users

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.conexa.conexachallenge.data.api.model.response.users.Address
import com.conexa.conexachallenge.data.api.model.response.users.Company
import com.conexa.conexachallenge.data.api.model.response.users.Geo
import com.conexa.conexachallenge.data.api.model.response.users.Login
import com.conexa.conexachallenge.data.api.model.response.users.UserResponse
import com.conexa.conexachallenge.data.feature.users.UsersRepository
import com.conexa.conexachallenge.domain.model.ResultNews
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class GetUsersUseCaseTest {

    @RelaxedMockK
    @MockK
    private lateinit var usersRepository: UsersRepository

    lateinit var getUsersUseCase: GetUsersUseCase

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setup() {
        MockKAnnotations.init(this)
        usersRepository = mockk()
        getUsersUseCase = GetUsersUseCase(usersRepository)
        Dispatchers.setMain(Dispatchers.Unconfined)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `testInvokeReturnsErrorWhenRepositoryFails`() = runTest {
        // Given
        val error = Throwable("Test error")
        coEvery { usersRepository.getUsers() } returns ResultNews.Error(error)

        // When
        val result = getUsersUseCase()

        // Then
        assertTrue(result is ResultNews.Error)
        assertEquals(error, (result as ResultNews.Error).exception)
    }

    @Test
    fun `testInvokeReturnsSuccessWhenRepositorySucceeds`() = runTest {
        // Given
        val userList = listOf(
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
        coEvery { usersRepository.getUsers() } returns ResultNews.Success(userList)

        // When
        val result = getUsersUseCase()

        // Then
        assertTrue(result is ResultNews.Success)
        assertEquals(userList, (result as ResultNews.Success).data)
    }
}
