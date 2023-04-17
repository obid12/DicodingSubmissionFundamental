package com.example.submissionandroidfundamental.data.model.response

import com.example.submissionandroidfundamental.domain.entity.SearchUserEntity
import com.example.submissionandroidfundamental.domain.entity.UserSearchEntity
import com.google.gson.annotations.SerializedName

data class SearchUserResponse(
    @field:SerializedName("items")
    val items: ArrayList<UserSearch>?,
) {
    companion object {
        fun transform(response: SearchUserResponse) = SearchUserEntity(
            UserSearch.toListUserSearchEntity(response.items ?: arrayListOf())
        )
    }
}

data class UserSearch(
    @field:SerializedName("avatar_url")
    val avatarUrl: String? = "",
    @field:SerializedName("login")
    val login: String? = ""
) {
    companion object {

        fun toListUserSearchEntity(response: ArrayList<UserSearch>): ArrayList<UserSearchEntity> {
            val result: ArrayList<UserSearchEntity> = arrayListOf()
            response.forEach {
                result.add(toUserSearchEntity(it))
            }
            return result
        }

        fun toUserSearchEntity(response: UserSearch) = UserSearchEntity(
            response.avatarUrl ?: "",
            response.login ?: ""
        )
    }

}
