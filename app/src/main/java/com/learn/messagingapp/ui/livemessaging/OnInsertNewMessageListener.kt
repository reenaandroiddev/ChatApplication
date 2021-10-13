package com.learn.messagingapp.ui.livemessaging

import com.learn.messagingapp.domain.entities.MessageContent

interface OnInsertNewMessageListener {
    fun insertMessage(messageContent: MessageContent)
}