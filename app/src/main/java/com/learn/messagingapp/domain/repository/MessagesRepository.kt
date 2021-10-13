package com.learn.messagingapp.domain.repository

import com.learn.messagingapp.domain.entities.MessageContent
import com.learn.messagingapp.domain.result.Results

interface MessagesRepository {
    suspend fun getMessages(): Results<HashMap<String,List<MessageContent>>>
}