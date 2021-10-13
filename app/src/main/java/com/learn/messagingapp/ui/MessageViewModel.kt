package com.learn.messagingapp.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.learn.messagingapp.domain.entities.MessageContent
import com.learn.messagingapp.domain.result.Results
import com.learn.messagingapp.domain.usecase.GetMessagesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class MessageViewModel @Inject constructor(private val getMessagesUseCase: GetMessagesUseCase) :
    ViewModel() {

    private lateinit var job: Job
    private var historyHashMap = MutableLiveData<HashMap<String, List<MessageContent>>>()

    var historyList = MutableLiveData<List<List<MessageContent>>>()
    var liveChatList = MutableLiveData<List<MessageContent>>()
    var error = MutableLiveData<String>()
    val isLoading = MutableLiveData(true)

    init {
        getChatHistory()
    }

    fun getChatHistory() {
        job = viewModelScope.launch {
            val result = getMessagesUseCase.invoke()

            try {
                withContext(Dispatchers.Main)
                {
                    isLoading.value = false
                    when (result) {
                        is Results.Success -> {
                            historyHashMap.value = result.data
                            convertHashMapToList(historyHashMap.value!!)
                        }
                        is Results.Error -> {
                            error.value = result.exception.message
                        }
                    }
                }
            } catch (e: Exception) {
                error.value = e.message
            }
        }
    }

    fun updateHistory(messageContent: MessageContent) {
        val list = historyHashMap.value?.get(messageContent.receiver.userName) as ArrayList
        list.add(messageContent)
        val tempMap = historyHashMap.value
        tempMap!![messageContent.receiver.userName] = list
        historyHashMap.value = tempMap
        convertHashMapToList(historyHashMap.value!!)
    }

    private fun convertHashMapToList(map: HashMap<String, List<MessageContent>>) {
        val list = ArrayList<List<MessageContent>>()
        map.values.forEach {
            list.add(it)
        }
        historyList.value = list
        isLoading.value = false
    }

    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }
}