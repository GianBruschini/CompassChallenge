package com.conexa.conexachallenge.presentation.user

import android.content.Context
import org.junit.Assert.*

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.conexa.conexachallenge.data.api.model.response.users.Address
import com.conexa.conexachallenge.data.api.model.response.users.Company
import com.conexa.conexachallenge.data.api.model.response.users.Geo
import com.conexa.conexachallenge.data.api.model.response.users.Login
import com.conexa.conexachallenge.data.api.model.response.users.UserResponse
import com.conexa.conexachallenge.domain.model.ResultNews
import com.conexa.conexachallenge.domain.usecase.users.GetUsersUseCase
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.*
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.Response

@RunWith(MockitoJUnitRunner::class)
class UsersViewModelTest {

    @RelaxedMockK
    private lateinit var getUsersUseCase: GetUsersUseCase

    @RelaxedMockK
    private lateinit var usersViewModel: UsersViewModel

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
        usersViewModel = UsersViewModel(getUsersUseCase)
        Dispatchers.setMain(Dispatchers.Unconfined)
    }

    @After
    fun onAfter() {
        Dispatchers.resetMain()
    }

    @Test
    fun `fetch users successful`() =
        runTest {
            // Given
            val userList = listOf(
                UserResponse(
                    id = 1,
                    firstName = "John",
                    lastName = "Doe",
                    email = "john.doe@example.com",
                    birthDate = "1990-01-01",
                    login = Login("","","","","",""),
                    address = Address("","","","",Geo("","")),
                    phone = "1234567890",
                    website = "www.example.com",
                    company = Company("","","")
                ),
                UserResponse(
                    id = 2,
                    firstName = "Jane",
                    lastName = "Doe",
                    email = "jane.doe@example.com",
                    birthDate = "1992-02-02",
                    login = Login("","","","","",""),
                    address = Address("","","","",Geo("","")),
                    phone = "9876543210",
                    website = "www.example.org",
                    company = Company("","","")
                )
            )
            coEvery {
                getUsersUseCase.invoke()
            } returns ResultNews.Success(userList)


            // When
            usersViewModel.fetchUsers()

            // Then
            val uiStateObserver = mock(Observer::class.java) as Observer<UsersUiState>
            advanceTimeBy(1000)
            assert(usersViewModel.uiState.value.loading)
            advanceUntilIdle()
            assert(!usersViewModel.uiState.value.loading)
            assert(usersViewModel.uiState.value.users.isNotEmpty())
            assert(usersViewModel.uiState.value.errorMessage.isNullOrEmpty())

        }


}
