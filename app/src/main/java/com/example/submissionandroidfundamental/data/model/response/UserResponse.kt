package com.example.submissionandroidfundamental.data.model.response


import com.example.submissionandroidfundamental.domain.entity.UserEntity
import com.google.gson.annotations.SerializedName

data class UserResponse(
    @SerializedName("login")
    val login: String? = "",
    @SerializedName("id")
    val id: Int? = 0,
    @SerializedName("node_id")
    val nodeId: String? = "",
    @SerializedName("avatar_url")
    val avatarUrl: String? = "",
    @SerializedName("gravatar_id")
    val gravatarId: String? = "",
    @SerializedName("type")
    val type: String? = "",
    @SerializedName("site_admin")
    val siteAdmin: Boolean? = true,
    @SerializedName("name")
    val name: String? = "",
    @SerializedName("company")
    val company: String? = "",
    @SerializedName("blog")
    val blog: String? = "",
    @SerializedName("location")
    val location: String? = "",
    @SerializedName("email")
    val email: String? = "",
    @SerializedName("hireable")
    val hireable: String? = "",
    @SerializedName("bio")
    val bio: String? = "",
    @SerializedName("twitter_username")
    val twitterUsername: String? = "",
    @SerializedName("public_repos")
    val publicRepos: Int? = 0,
    @SerializedName("public_gists")
    val publicGists: Int? = 0,
    @SerializedName("followers")
    val followers: Int? = 0,
    @SerializedName("following")
    val following: Int? = 0,
    @SerializedName("created_at")
    val createdAt: String? = "",
    @SerializedName("updated_at")
    val updatedAt: String? = ""
) {
    fun toUserEntity() = UserEntity(
        login ?: "",
        name ?: "",
        location ?: "",
        publicRepos ?: 0,
        company ?: "",
        followers ?: 0,
        following ?: 0,
        avatar = 0,
        avatarUrl ?: ""
    )
}