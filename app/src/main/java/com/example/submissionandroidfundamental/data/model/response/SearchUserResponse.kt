package com.example.submissionandroidfundamental.data.model.response

import com.example.submissionandroidfundamental.domain.entity.UserSearchEntity
import com.google.gson.annotations.SerializedName

data class SearchUserResponse(
    @field:SerializedName("items")
    val items: MutableList<UserSearch>?,
)

data class UserSearch(
    @field:SerializedName("avatar_url")
    val avatarUrl: String? = "",
    @field:SerializedName("login")
    val login: String? = ""
) {
    fun toUserSearchEntity() = UserSearchEntity(
        avatarUrl ?: "",
        login ?: ""
    )
}
