package com.conexa.conexachallenge.data.api.model.response.users

data class Login(
    val uuid: String,
    val username: String,
    val password: String,
    val md5: String,
    val sha1: String,
    val registered: String
)