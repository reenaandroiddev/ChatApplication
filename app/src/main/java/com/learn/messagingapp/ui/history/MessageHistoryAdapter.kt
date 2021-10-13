package com.learn.messagingapp.ui.history

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.learn.messagingapp.R
import com.learn.messagingapp.databinding.ListItemMessageHistoryBinding
import com.learn.messagingapp.domain.entities.MessageContent

class MessageHistoryAdapter(val clickListener: OnMessageClickListener) :
    RecyclerView.Adapter<MessageHistoryViewHolder>() {
    private var messageContentList: List<List<MessageContent>> = mutableListOf()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageHistoryViewHolder {
        val binding: ListItemMessageHistoryBinding = DataBindingUtil.inflate(
            LayoutInflater.from(
                parent.context
            ),
            R.layout.list_item_message_history,
            parent,
            false
        )
        return MessageHistoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MessageHistoryViewHolder, position: Int) {
        val messageList = messageContentList[position]
        holder.bind(messageContentList[position][messageList.size - 1])
        holder.itemView.setOnClickListener {
            clickListener.onChatClick(messageContentList[position])
        }
    }

    override fun getItemCount(): Int {
        return messageContentList.size
    }

    fun setListItem(list: List<List<MessageContent>>) {
        // Apply Diff Util
        messageContentList = list
        notifyDataSetChanged()
    }

}