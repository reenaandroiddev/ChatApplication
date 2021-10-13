package com.learn.messagingapp.ui.history

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.learn.messagingapp.R
import com.learn.messagingapp.domain.entities.MessageContent
import com.learn.messagingapp.ui.MessageViewModel
import dagger.hilt.android.AndroidEntryPoint

/**
 * A simple [Fragment] subclass.
 * Use the [MessageHistoryFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
@AndroidEntryPoint
class MessageHistoryFragment : Fragment(), OnMessageClickListener {

    private val sharedViewModel: MessageViewModel by activityViewModels()
    private val historyAdapter: MessageHistoryAdapter by lazy {
        MessageHistoryAdapter(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_message_history, container, false)

        view.findViewById<RecyclerView>(R.id.rvHistory).apply {
            adapter = historyAdapter
        }
        return view
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeLiveData()
    }

    override fun onChatClick(item: List<MessageContent>) {
        sharedViewModel.liveChatList.value = item
        navigateToLiveMessage()
    }

    private fun navigateToLiveMessage() {
        val action =
            MessageHistoryFragmentDirections.actionMessageHistoryFragmentToLiveMessageFragment()
        findNavController().navigate(action)
    }

    private fun observeLiveData() {
        sharedViewModel.historyList.observe(viewLifecycleOwner, {
            historyAdapter.setListItem(it)
        })
    }
}