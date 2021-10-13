package com.learn.messagingapp.data.repository

import com.learn.messagingapp.data.mockdataprovider.getInitialChat
import com.learn.messagingapp.domain.entities.MessageContent
import com.learn.messagingapp.domain.repository.MessagesRepository
import com.learn.messagingapp.domain.result.Results
import javax.inject.Inject

class MessageRepositoryImpl @Inject constructor() : MessagesRepository {
    override suspend fun getMessages(): Results<HashMap<String, List<MessageContent>>> {
        return Results.Success(getInitialChat())
    }
}