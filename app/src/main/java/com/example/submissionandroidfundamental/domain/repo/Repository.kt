package com.example.submissionandroidfundamental.domain.repo

import com.example.submissionandroidfundamental.data.model.response.SearchUserResponse
import com.example.submissionandroidfundamental.data.model.response.UserResponse
import com.example.submissionandroidfundamental.data.model.response.UserSearch
import com.example.submissionandroidfundamental.domain.entity.UserEntity
import com.example.submissionandroidfundamental.domain.entity.UserSearchEntity
import com.example.submissionandroidfundamental.utils.result.Result
import kotlinx.coroutines.flow.Flow


interface Repository {
    suspend fun getDetailUser(name: String):
            Flow<Result<UserEntity, UserResponse>>

    suspend fun searchUser(searchQuery: String?):
            Flow<Result<MutableList<UserSearchEntity>, SearchUserResponse>>

    suspend fun getListFollowers(username: String):
            Flow<Result<
                    MutableList<UserSearchEntity>, MutableList<UserSearch>>>

    suspend fun getListFollowing(username: String):
            Flow<Result<
                    MutableList<UserSearchEntity>, MutableList<UserSearch>>>
}