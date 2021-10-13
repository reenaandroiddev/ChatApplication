package com.learn.messagingapp.ui.livemessaging

import androidx.recyclerview.widget.RecyclerView
import com.learn.messagingapp.databinding.ListItemLiveMessageBinding
import com.learn.messagingapp.domain.entities.MessageContent

class LiveMessageViewHolder(
    private val binding: ListItemLiveMessageBinding
) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(messageContent: MessageContent) {
        binding.messageContent = messageContent
    }
}