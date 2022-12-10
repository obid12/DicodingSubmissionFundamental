package com.example.submissionandroidfundamental.data.api

import com.example.submissionandroidfundamental.data.model.response.SearchUserResponse
import com.example.submissionandroidfundamental.data.model.response.UserResponse
import com.example.submissionandroidfundamental.data.model.response.UserSearch
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiClient {

    @GET("users/{name}")
    suspend fun getDetailUser(
        @Path("name") name: String
    ): Response<UserResponse>

    @GET("search/users")
    suspend fun searchUser(
        @Query("q") searchQuery: String?
    ): Response<SearchUserResponse>

    @GET("users/{username}/followers")
    suspend fun getListFollowers(
        @Path("username") username: String
    ): Response<List<UserSearch>>

    @GET("users/{username}/following")
    suspend fun getListFollowing(
        @Path("username") username: String
    ): Response<MutableList<UserSearch>>
}