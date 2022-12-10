package com.example.submissionandroidfundamental.presentation.detailuser

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.submissionandroidfundamental.domain.entity.UserEntity
import com.example.submissionandroidfundamental.domain.usecase.UseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailUserViewModel @Inject constructor(
    private val useCase: UseCase
) : ViewModel() {

    private val _state = MutableStateFlow<DetailUserState>(DetailUserState.Init)
    val state get() = _state

    private val _data = MutableStateFlow<UserEntity?>(null)
    val data get() = _data

    private fun loading() {
        _state.value = DetailUserState.Loading()
    }

    private fun success(dataEntity: UserEntity) {
        _state.value = DetailUserState.Success(dataEntity)
        _data.value = dataEntity
    }

    fun detailUser(name: String) {
        viewModelScope.launch {
            useCase.getDetailUSer(name)
                .onStart {
                    loading()

                }.catch {

                }.collect { result ->
                    when (result) {
                        is com.example.submissionandroidfundamental.utils.result.
                        Result.Success -> success(result.data)
                        is com.example.submissionandroidfundamental.utils.result.
                        Result.Error -> Log.v("DATA", "Error")
                    }
                }
        }
    }


}

sealed class DetailUserState {
    object Init : DetailUserState()
    data class Loading(val loading: Boolean = true) : DetailUserState()
    data class Success(val dataEntity: UserEntity) : DetailUserState()

}