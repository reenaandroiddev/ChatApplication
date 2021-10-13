package com.learn.messagingapp.ui.livemessaging

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.learn.messagingapp.R
import com.learn.messagingapp.databinding.FragmentLiveMessageBinding
import com.learn.messagingapp.domain.entities.MessageContent
import com.learn.messagingapp.domain.entities.User
import com.learn.messagingapp.ui.MessageViewModel

class LiveMessageFragment : Fragment() {

    private val sharedViewModel: MessageViewModel by activityViewModels()
    private val liveMessageAdapter: LiveMessageAdapter by lazy {
        LiveMessageAdapter()
    }
    private lateinit var binding: FragmentLiveMessageBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_live_message, container, false)

        sharedViewModel.isLoading.value = true
        setUpRecyclerView()
        binding.btnSend.setOnClickListener {
            insertNewMessage()
        }
        return binding.root
    }

    private fun setUpRecyclerView() {
        binding.rvLiveMessage.apply {
            adapter = liveMessageAdapter
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getMessagesList()

    }

    private fun getMessagesList() {
        liveMessageAdapter.setItems(sharedViewModel.liveChatList.value)
        sharedViewModel.isLoading.value = false
    }

    private fun insertNewMessage() {
        val message = binding.edtMessage.text.toString()
        if (message.isNotEmpty()) {
            val senderUser = "Me"
            val receiverUser = sharedViewModel.liveChatList.value?.get(0)?.receiver?.userName
            val sender = User(senderUser, message, System.currentTimeMillis())
            val receiver = receiverUser?.let { User(it, message, System.currentTimeMillis() + 1) }

            receiver?.let { MessageContent(sender, it) }?.let {
                liveMessageAdapter.insertSingleMessage(
                    it
                )
                sharedViewModel.updateHistory(it)
            }
        }
        binding.rvLiveMessage.smoothScrollToPosition(binding.rvLiveMessage.bottom)
    }

}