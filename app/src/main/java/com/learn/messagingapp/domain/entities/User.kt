package com.learn.messagingapp.domain.entities

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class User(val userName: String, val message: String, val time: Long?) : Parcelable