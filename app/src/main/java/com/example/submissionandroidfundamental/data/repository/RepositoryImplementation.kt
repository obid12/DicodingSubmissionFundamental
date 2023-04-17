package com.example.submissionandroidfundamental.data.repository

import com.example.submissionandroidfundamental.data.api.ApiClient
import com.example.submissionandroidfundamental.data.model.response.SearchUserResponse
import com.example.submissionandroidfundamental.data.model.response.UserResponse
import com.example.submissionandroidfundamental.data.model.response.UserSearch
import com.example.submissionandroidfundamental.domain.entity.UserEntity
import com.example.submissionandroidfundamental.domain.entity.UserSearchEntity
import com.example.submissionandroidfundamental.domain.repo.Repository
import com.example.submissionandroidfundamental.utils.result.Result
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RepositoryImplementation @Inject constructor(
    private val apiClient: ApiClient
) : Repository {
    override suspend fun getDetailUser(name: String): Flow<Result<UserEntity, UserResponse>> {
        return flow {

            val response = apiClient.getDetailUser(name)
            delay(1000)
            if (response.isSuccessful) {
                val data = response.body()?.toUserEntity()
                emit(
                    Result.Success(data as UserEntity)
                )
            } else {
                response.errorBody()
            }
        }
    }

    override suspend fun searchUser(searchQuery: String?): Flow<Result<ArrayList<UserSearchEntity>, SearchUserResponse>> {
        return flow {
            val response = apiClient.searchUser(searchQuery)
            delay(1000)
            if (response.isSuccessful) {
                response.body()?.let {
                    val data = SearchUserResponse.transform(it)
                    emit(Result.Success(data.items))
                }

            } else {
                response.errorBody()
            }
        }
    }

    override suspend fun getListFollowers(username: String): Flow<Result<ArrayList<UserSearchEntity>, ArrayList<UserSearch>>> {
        return flow {
            val response = apiClient.getListFollowers(username)
            delay(1000)
            if (response.isSuccessful) {
                val data = arrayListOf<UserSearchEntity>()
                val body = response.body()
                body?.forEach {
                    data.add(UserSearch.toUserSearchEntity(it))
                }
                emit(Result.Success(data))
            } else {
                response.errorBody()
            }
        }
    }

    override suspend fun getListFollowing(username: String): Flow<Result<ArrayList<UserSearchEntity>, ArrayList<UserSearch>>> {
        return flow {
            val response = apiClient.getListFollowing(username)
            delay(1000)
            if (response.isSuccessful) {
                val data = arrayListOf<UserSearchEntity>()
                val body = response.body()
                body?.forEach {
                    data.add(UserSearch.toUserSearchEntity(it))
                }
                emit(Result.Success(data))
            } else {
                response.errorBody()
            }
        }
    }

}