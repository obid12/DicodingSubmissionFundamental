package com.example.submissionandroidfundamental.domain.entity

import android.os.Parcelable
import androidx.annotation.Keep
import kotlinx.android.parcel.Parcelize

@Suppress("DEPRECATED_ANNOTATION")
@Keep
@Parcelize
data class UserEntity(
    val username: String,
    val name: String,
    val location: String,
    val repository: Int,
    val company: String,
    val follower: Int,
    val following: Int,
    val avatar: Int,
    val avatarUrl:String
) : Parcelable