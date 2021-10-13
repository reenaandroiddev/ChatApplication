package com.learn.messagingapp.ui.livemessaging

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.learn.messagingapp.R
import com.learn.messagingapp.databinding.ListItemLiveMessageBinding
import com.learn.messagingapp.domain.entities.MessageContent

class LiveMessageAdapter() :
    RecyclerView.Adapter<LiveMessageViewHolder>() {

    private var messageContentList: ArrayList<MessageContent> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LiveMessageViewHolder {
        val binding: ListItemLiveMessageBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.list_item_live_message, parent, false
        )
        return LiveMessageViewHolder(binding)
    }

    override fun onBindViewHolder(holder: LiveMessageViewHolder, position: Int) {
        holder.bind(messageContentList[position])
    }

    override fun getItemCount(): Int {
        return messageContentList.size
    }

    fun setItems(list: List<MessageContent>?) {
        list?.forEach { item -> messageContentList.add(item) }
        notifyDataSetChanged()
    }

    fun insertSingleMessage(messageContent: MessageContent) {
        messageContentList.add(messageContent)
        notifyItemInserted(messageContentList.size)
    }
}