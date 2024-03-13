package com.conexa.conexachallenge.data.api.model.response

data class NetworkResponse<T>(
    var code: Long,
    var description: String,
    var done: Boolean,
    var notExpected: Any? = null,
    val data: T,
)