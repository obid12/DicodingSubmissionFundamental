package com.example.submissionandroidfundamental.domain.entity

import com.example.submissionandroidfundamental.data.model.response.UserSearch


data class SearchUserEntity(
    val items: ArrayList<UserSearchEntity>,
)

data class UserSearchEntity(
    val avatarUrl: String,
    val login: String
)
