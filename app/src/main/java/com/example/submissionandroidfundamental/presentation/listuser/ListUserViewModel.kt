package com.example.submissionandroidfundamental.presentation.listuser

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.submissionandroidfundamental.domain.entity.UserSearchEntity
import com.example.submissionandroidfundamental.domain.usecase.UseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListUserViewModel @Inject constructor(
    private val useCase: UseCase
) : ViewModel() {
    private val _state = MutableStateFlow<ListUserState>(ListUserState.Init)
    val state get() = _state

    private val _data = MutableStateFlow<MutableList<UserSearchEntity>>(mutableListOf())
    val data: StateFlow<MutableList<UserSearchEntity>> get() = _data


    private fun loading() {
        _state.value = ListUserState.Loading()
    }

    private fun success(dataEntity: MutableList<UserSearchEntity>) {
        _state.value = ListUserState.Success(dataEntity)
        _data.value = dataEntity
    }

    private fun errorData() {
        _state.value = ListUserState.Error("Ada masalah")

    }


    fun searchUSer(searchQuery: String?) {
        viewModelScope.launch {
            useCase.searchUser(searchQuery)
                .onStart {
                    loading()

                }.catch {
                    errorData()
                }
                .collect { result ->
                    when (result) {
                        is com.example.submissionandroidfundamental.utils.result.
                        Result.Success -> success(result.data)
                        is com.example.submissionandroidfundamental.utils.result.
                        Result.Error -> {
                        }
                    }
                }
        }
    }
}

sealed class ListUserState {
    object Init : ListUserState()
    data class Loading(val loading: Boolean = true) : ListUserState()
    data class Success(val dataEntity: MutableList<UserSearchEntity>) : ListUserState()
    data class Error(val error: String) : ListUserState()
}