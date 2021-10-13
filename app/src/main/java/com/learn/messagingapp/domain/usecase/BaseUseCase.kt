package com.learn.messagingapp.domain.usecase

import com.learn.messagingapp.domain.result.Results


interface BaseUseCase<R : Any> {
    suspend fun invoke(): Results<R>
}