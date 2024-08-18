package org.fa.blogmultiplatform.repositories.user

import com.mongodb.reactivestreams.client.MongoCollection
import kotlinx.coroutines.flow.Flow
import org.fa.blogmultiplatform.models.User
import org.fa.blogmultiplatform.util.State

interface UserRepo {
    val userCollection: MongoCollection<User>
    fun createUser(user: User): Flow<State<Boolean>>
    fun isUserExists(email: String): Flow<State<Boolean>>
}