package com.learn.messagingapp.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.learn.messagingapp.data.mockdataprovider.getInitialChat
import com.learn.messagingapp.data.mockdataprovider.getMessageContent
import com.learn.messagingapp.data.mockdataprovider.getUser
import com.learn.messagingapp.domain.result.Results
import com.learn.messagingapp.domain.usecase.GetMessagesUseCase
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class MessageViewModelTest {

    private val getMessagesUseCase: GetMessagesUseCase = mock()
    private lateinit var messageViewModel: MessageViewModel

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @Before
    fun setUp() {
        messageViewModel = MessageViewModel(getMessagesUseCase)
    }

    @Test
    fun `show recent message from message list`() {
        mainCoroutineRule.runBlockingTest {
            whenever(getMessagesUseCase.invoke()).thenReturn(Results.Success(getInitialChat()))

            messageViewModel.getChatHistory()

            val list = messageViewModel.historyList.getOrAwaitValue()

            val list1 = list[0]
            val size = list1.size

            assertEquals("John", list1[size - 1].receiver.userName)
            assertEquals("How are you?", list1[size - 1].receiver.message)

            val list2 = list[1]
            val size2 = list2.size

            assertEquals("Kent", list2[size2 - 1].receiver.userName)
            assertEquals("Hope you are doing good?", list2[size2 - 1].receiver.message)
        }
    }

    @Test
    fun `insert new message in history and verify`() {
        mainCoroutineRule.runBlockingTest {

            whenever(getMessagesUseCase.invoke()).thenReturn(Results.Success(getInitialChat()))

            messageViewModel.getChatHistory()
            messageViewModel.updateHistory(
                getMessageContent(
                    getUser(
                        "Me",
                        "Are you planning for dinner?",
                        System.currentTimeMillis() - 3000
                    ),
                    getUser("Reena", "Sure we can plan", System.currentTimeMillis() - 2000)
                )
            )

        }

        val list = messageViewModel.historyList.getOrAwaitValue()

        val list2 = list[2]
        val size2 = list2.size

        assertEquals("Reena", list2[size2 - 1].receiver.userName)
        assertEquals("Sure we can plan", list2[size2 - 1].receiver.message)

    }

    @Test
    fun `show error when message list is empty or in case of exception`() {
        mainCoroutineRule.runBlockingTest {

            whenever(getMessagesUseCase.invoke()).thenReturn(Results.Error(Throwable("No records found")))

            messageViewModel.getChatHistory()

            val error = messageViewModel.error.getOrAwaitValue()
            assertEquals("No records found", error)
        }
    }
}