package org.fa.blogmultiplatform.repositories.user

import com.mongodb.reactivestreams.client.MongoCollection
import kotlinx.coroutines.flow.Flow
import org.fa.blogmultiplatform.models.SignInUserDTO
import org.fa.blogmultiplatform.models.SignUpUserDTO
import org.fa.blogmultiplatform.util.State

actual interface UserRepo {
    fun <T> getUserCollection(type: Class<T>): MongoCollection<T>
    actual fun signIn(req: SignInUserDTO): Flow<State<Boolean>>
    actual fun signUp(req: SignUpUserDTO): Flow<State<Boolean>>
    actual fun isExist(email: String): Flow<State<Boolean>>
}