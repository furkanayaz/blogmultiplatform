package org.fa.blogmultiplatform.repositories.user

import com.mongodb.reactivestreams.client.MongoCollection
import kotlinx.coroutines.flow.Flow
import org.fa.blogmultiplatform.models.User
import org.fa.blogmultiplatform.util.Response

interface UserRepo {
    val userCollection: MongoCollection<User>
    fun signInUser(user: User): Flow<Response<User?>>
    fun signUpUser(user: User): Flow<Response<Boolean>>
    fun isUserExists(email: String): Flow<Response<Boolean>>
}