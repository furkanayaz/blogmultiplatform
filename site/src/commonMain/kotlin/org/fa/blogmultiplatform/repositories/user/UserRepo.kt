package org.fa.blogmultiplatform.repositories.user

import kotlinx.coroutines.flow.Flow
import org.fa.blogmultiplatform.models.SignInUserDTO
import org.fa.blogmultiplatform.models.SignUpUserDTO
import org.fa.blogmultiplatform.util.State

expect interface UserRepo {
    fun signIn(req: SignInUserDTO): Flow<State<Boolean>>
    fun signUp(req: SignUpUserDTO): Flow<State<Boolean>>
    fun isExist(email: String): Flow<State<Boolean>>
}