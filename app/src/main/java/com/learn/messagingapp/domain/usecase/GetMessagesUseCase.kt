package com.learn.messagingapp.domain.usecase

import com.learn.messagingapp.domain.entities.MessageContent
import com.learn.messagingapp.domain.repository.MessagesRepository
import com.learn.messagingapp.domain.result.Results
import javax.inject.Inject

class GetMessagesUseCase @Inject constructor(private val repository: MessagesRepository) :
    BaseUseCase<HashMap<String,List<MessageContent>>> {

    override suspend fun invoke(): Results<HashMap<String,List<MessageContent>>> {
        return repository.getMessages()
    }
}