package org.fa.blogmultiplatform.pages.admin.register

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import org.fa.blogmultiplatform.models.User
import org.fa.blogmultiplatform.services.signUp
import org.fa.blogmultiplatform.util.UiState

class RegisterRepo {
    private val _signUpFlow = MutableStateFlow<UiState<Boolean>>(UiState.Idle())
    val signUpFlow get() = _signUpFlow.asStateFlow()

    suspend fun signUp(email: String, password: String) {
        signUp(User(email = email, password = password)).collect {
            when(it) {
                is UiState.Idle -> { /* NO-OP */ }
                is UiState.Error -> _signUpFlow.value = UiState.Error(it.exception)
                is UiState.Loading -> _signUpFlow.value = UiState.Loading()
                is UiState.Success -> _signUpFlow.value = UiState.Success(it.result.data ?: false)
            }
        }
    }

}