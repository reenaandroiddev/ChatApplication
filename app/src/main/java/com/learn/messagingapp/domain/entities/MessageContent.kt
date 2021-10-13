package com.learn.messagingapp.domain.entities

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MessageContent(
    val sender: User,
    val receiver: User
) : Parcelable
