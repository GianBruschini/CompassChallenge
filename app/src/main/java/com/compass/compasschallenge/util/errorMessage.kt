package com.compass.compasschallenge.util

import com.compass.compasschallenge.R
import com.compass.compasschallenge.domain.model.ResultNews
import com.compass.compasschallenge.domain.model.exception.ApiException

import java.net.UnknownHostException

fun ResultNews.Error.errorMessage(): BindingString {
    return this.exception.errorMessage()
}

fun Throwable.errorMessage(): BindingString {
    return when {
        this is ApiException && !message.isNullOrBlank() -> TextBindingString(this.message!!)
        this is UnknownHostException -> ResourceBindingString(R.string.error_network_connection)
        else -> ResourceBindingString(R.string.error_unknown)
    }
}