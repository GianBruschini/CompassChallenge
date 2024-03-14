package com.conexa.conexachallenge.data.api.model.response.users

import com.conexa.conexachallenge.data.api.model.response.users.Address
import com.conexa.conexachallenge.data.api.model.response.users.Company
import com.conexa.conexachallenge.data.api.model.response.users.Login

data class UserResponse(
    val id: Int,
    val firstName: String,
    val lastName: String,
    val email: String,
    val birthDate: String,
    val login: Login,
    val address: Address,
    val phone: String,
    val website: String,
    val company: Company
)








