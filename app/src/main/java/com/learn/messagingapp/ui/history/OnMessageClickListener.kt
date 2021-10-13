package com.learn.messagingapp.ui.history

import com.learn.messagingapp.domain.entities.MessageContent

interface OnMessageClickListener {
    fun onChatClick(item: List<MessageContent>)
}