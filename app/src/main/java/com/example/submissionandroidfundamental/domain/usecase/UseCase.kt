package com.example.submissionandroidfundamental.domain.usecase

import com.example.submissionandroidfundamental.data.model.response.SearchUserResponse
import com.example.submissionandroidfundamental.data.model.response.UserResponse
import com.example.submissionandroidfundamental.data.model.response.UserSearch
import com.example.submissionandroidfundamental.domain.entity.UserEntity
import com.example.submissionandroidfundamental.domain.entity.UserSearchEntity
import com.example.submissionandroidfundamental.domain.repo.Repository
import com.example.submissionandroidfundamental.utils.result.Result
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UseCase @Inject constructor(
    private val repository: Repository
) {
    suspend fun getDetailUSer(
        name: String
    ): Flow<Result<UserEntity, UserResponse>> {
        return repository.getDetailUser(name)
    }

    suspend fun searchUser(searchQuery: String?):
            Flow<Result<MutableList<UserSearchEntity>, SearchUserResponse>> {
        return repository.searchUser(searchQuery)
    }

    suspend fun getListFollowers(username: String):
            Flow<Result<MutableList<UserSearchEntity>, MutableList<UserSearch>>> {
        return repository.getListFollowers(username)
    }

    suspend fun getListFollowing(username: String):
            Flow<Result<MutableList<UserSearchEntity>, MutableList<UserSearch>>> {
        return repository.getListFollowing(username)
    }
}