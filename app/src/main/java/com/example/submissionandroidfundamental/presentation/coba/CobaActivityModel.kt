package com.example.submissionandroidfundamental.presentation.coba

import android.os.Parcelable
import com.example.submissionandroidfundamental.domain.entity.SearchUserEntity
import com.example.submissionandroidfundamental.domain.entity.UserSearchEntity
import kotlinx.parcelize.Parcelize
import javax.inject.Inject

class CobaActivityModel @Inject constructor() {
    var data: SearchUserEntity? = null
    fun transform(response: ArrayList<UserSearchEntity>?): ArrayList<UserItemModel> {
        val result: ArrayList<UserItemModel> = arrayListOf()
        response?.forEach {
            result.add(transform(it))
        }
        return result
    }

    private fun transform(response: UserSearchEntity) = UserItemModel(
        response.avatarUrl,
        response.login
    )

    fun fillData(searchUserEntity: SearchUserEntity) {
        searchUserEntity.let {
            data = it
        }

    }


}

@Parcelize
open class UserItemModel(
    val avatarUrl: String,
    val login: String
) : Parcelable