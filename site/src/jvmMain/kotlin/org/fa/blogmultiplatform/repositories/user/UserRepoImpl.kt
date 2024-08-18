package org.fa.blogmultiplatform.repositories.user

import com.mongodb.reactivestreams.client.MongoCollection
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.reactive.awaitFirst
import kotlinx.coroutines.reactive.awaitFirstOrNull
import org.fa.blogmultiplatform.api.BlogDB
import org.fa.blogmultiplatform.models.User
import org.fa.blogmultiplatform.util.State
import org.litote.kmongo.and
import org.litote.kmongo.eq

class UserRepoImpl: UserRepo {
    override val userCollection: MongoCollection<User>
        get() = BlogDB.getCollection(BlogDB.USERS)

    override fun createUser(user: User): Flow<State<Boolean>> = callbackFlow {
        send(State.Loading())

        try {
            val response = userCollection.insertOne(user).awaitFirst()
            send(State.Success(response.wasAcknowledged()))
        } catch (e: Exception) {
            e.printStackTrace()
            send(State.Error(e))
        }

        awaitClose {
            launch { send(State.Error(Exception("Occurred an unknown error."))) }
        }

    }

    override fun isUserExists(email: String): Flow<State<Boolean>> = callbackFlow {
        send(State.Loading())

        try {
            val response = userCollection.find(and(User::email eq email)).awaitFirstOrNull()
            send(State.Success(response != null))
        } catch (e: Exception) {
            e.printStackTrace()
            send(State.Error(e))
        }

        awaitClose {
            launch { send(State.Error(Exception("Occurred an unknown error."))) }
        }
    }
}