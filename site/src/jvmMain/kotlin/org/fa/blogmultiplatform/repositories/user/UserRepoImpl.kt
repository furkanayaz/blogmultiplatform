package org.fa.blogmultiplatform.repositories.user

import com.mongodb.reactivestreams.client.MongoCollection
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.reactive.awaitFirst
import kotlinx.coroutines.reactive.awaitFirstOrNull
import org.fa.blogmultiplatform.api.BlogDB
import org.fa.blogmultiplatform.exceptions.FlowClosedException
import org.fa.blogmultiplatform.models.SignInUserDTO
import org.fa.blogmultiplatform.models.SignUpUserDTO
import org.fa.blogmultiplatform.util.State
import org.litote.kmongo.and
import org.litote.kmongo.eq

class UserRepoImpl: UserRepo {
    override fun <T> getUserCollection(type: Class<T>): MongoCollection<T> {
        return BlogDB.getCollection(BlogDB.USERS, type)
    }

    override fun signIn(req: SignInUserDTO): Flow<State<Boolean>> = callbackFlow {
        send(State.Loading())

        try {
            val response = getUserCollection(SignInUserDTO::class.java).find(
                and(
                    SignUpUserDTO::email eq req.email,
                    SignUpUserDTO::password eq req.password
                )
            ).awaitFirstOrNull()

            send(State.Success(response != null))
        } catch (e: Exception) {
            e.printStackTrace()
            send(State.Error(e))
        }

        awaitClose {
            launch { send(State.Error(FlowClosedException())) }
        }
    }

    override fun signUp(req: SignUpUserDTO): Flow<State<Boolean>> = callbackFlow {
        send(State.Loading())

        try {
            val response = getUserCollection(SignUpUserDTO::class.java).insertOne(req).awaitFirst()
            send(State.Success(response.wasAcknowledged()))
        } catch (e: Exception) {
            e.printStackTrace()
            send(State.Error(e))
        }

        awaitClose {
            launch { send(State.Error(FlowClosedException())) }
        }

    }

    override fun isExist(email: String): Flow<State<Boolean>> = callbackFlow {

    }
}