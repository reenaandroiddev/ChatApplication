package com.learn.messagingapp.ui.history

import androidx.recyclerview.widget.RecyclerView
import com.learn.messagingapp.databinding.ListItemMessageHistoryBinding
import com.learn.messagingapp.domain.entities.MessageContent

class MessageHistoryViewHolder(private val binding: ListItemMessageHistoryBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(recentMessageContent: MessageContent) {
        binding.recentMessage = recentMessageContent
    }
}