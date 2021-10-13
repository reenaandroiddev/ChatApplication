package com.learn.messagingapp.di

import com.learn.messagingapp.data.repository.MessageRepositoryImpl
import com.learn.messagingapp.domain.repository.MessagesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Provides
    fun provideRepository(): MessagesRepository = MessageRepositoryImpl()
}