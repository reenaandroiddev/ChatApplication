package com.learn.messagingapp.domain.result

sealed class Results<out T : Any> {
    class Success<out T : Any>(val data: T) : Results<T>()
    class Error(val exception: Throwable) : Results<Nothing>()
}
