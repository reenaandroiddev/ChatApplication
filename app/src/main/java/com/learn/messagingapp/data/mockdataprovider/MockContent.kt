package com.learn.messagingapp.data.mockdataprovider

import com.learn.messagingapp.domain.entities.MessageContent
import com.learn.messagingapp.domain.entities.User

fun getInitialChat(): HashMap<String, List<MessageContent>> {
    val list = getListOfMessageContentUser1()
    return list.groupBy { it -> it.receiver.userName } as HashMap
}

fun getListOfMessageContentUser1(): ArrayList<MessageContent> = arrayListOf(
    getMessageContent(
        getUser("Me", "Hello", System.currentTimeMillis() - 5000),
        getUser("John", "Hello", System.currentTimeMillis() - 3000)
    ),
    getMessageContent(
        getUser("Me", "How are you?", System.currentTimeMillis() - 3000),
        getUser("John", "How are you?", System.currentTimeMillis() - 2000)
    ),
    getMessageContent(
        getUser("Me", "Hello", System.currentTimeMillis() - 5000),
        getUser("Kent", "Hello", System.currentTimeMillis() - 3000)
    ),
    getMessageContent(
        getUser("Me", "Hope you are doing good?", System.currentTimeMillis() - 3000),
        getUser("Kent", "Hope you are doing good?", System.currentTimeMillis() - 2000)
    ),
    getMessageContent(
        getUser("Me", "Hello", System.currentTimeMillis() - 5000),
        getUser("Reena", "Hello", System.currentTimeMillis() - 3000)
    ),
    getMessageContent(
        getUser("Me", "Are you planning for dinner?", System.currentTimeMillis() - 3000),
        getUser("Reena", "Yes", System.currentTimeMillis() - 2000)
    )
)


fun getMessageContent(sender: User, receiver: User) = MessageContent(sender, receiver)

fun getUser(userName: String, message: String, time: Long): User = User(userName, message, time)