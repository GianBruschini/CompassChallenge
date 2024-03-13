package com.conexa.conexachallenge.domain.model

sealed class ResultNews<out T> {
    data class Success<T>(var data: T) : ResultNews<T>()
    data class Error(var exception: Throwable) : ResultNews<Nothing>()

    override fun toString(): String {
        return when (this) {
            is Success<*> -> "Success[data=$data]"
            is Error -> "Error[exception=$exception]"
        }
    }
}

/**
 * `true` if [ResultNews] is of type [Success] & holds non-null [Success.data].
 */
val ResultNews<*>.succeeded
    get() = this is ResultNews.Success && data != null
