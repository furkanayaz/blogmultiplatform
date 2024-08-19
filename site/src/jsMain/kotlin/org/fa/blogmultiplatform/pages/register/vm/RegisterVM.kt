package org.fa.blogmultiplatform.pages.register.vm

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import org.fa.blogmultiplatform.models.SignInUserDTO
import org.fa.blogmultiplatform.models.SignUpUserDTO
import org.fa.blogmultiplatform.repositories.user.UserRepoImpl
import org.fa.blogmultiplatform.util.State

class RegisterVM {
    private val userRepo = UserRepoImpl()

    private val _signInFlow = MutableStateFlow<State<Boolean>>(State.Idle())
    val signInFlow get() = _signInFlow.asStateFlow()

    private val _signUpFlow = MutableStateFlow<State<Boolean>>(State.Idle())
    val signUpFlow get() = _signUpFlow.asStateFlow()

    // Ui States...
    var isRememberMe by mutableStateOf(false)
    var isFocusedSignIn by mutableStateOf(false)
    var isErrorExist by mutableStateOf("")

    suspend fun signIn(email: String, password: String) {
        userRepo.signIn(SignInUserDTO(email, password)).collect {
            when(it) {
                is State.Error -> _signInFlow.value = State.Error(it.exception)
                is State.Loading -> _signInFlow.value = State.Loading()
                is State.Success -> _signInFlow.value = State.Success(it.result)
                else -> { /* NO-OP */ }
            }
        }
    }

    suspend fun signUp(fullName: String, email: String, password: String) {
        userRepo.signUp(SignUpUserDTO(fullName = fullName, email = email, password = password)).collect {
            when(it) {
                is State.Error -> _signUpFlow.value = State.Error(it.exception)
                is State.Loading -> _signUpFlow.value = State.Loading()
                is State.Success -> _signUpFlow.value = State.Success(it.result)
                else -> { /* NO-OP */ }
            }
        }
    }
}