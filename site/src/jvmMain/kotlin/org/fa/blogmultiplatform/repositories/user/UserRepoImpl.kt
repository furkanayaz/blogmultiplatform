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
import org.fa.blogmultiplatform.util.Response
import org.litote.kmongo.and
import org.litote.kmongo.eq

class UserRepoImpl: UserRepo {
    override val userCollection: MongoCollection<User>
        get() = BlogDB.getCollection(BlogDB.USERS)

    override fun signInUser(user: User): Flow<Response<User?>> = callbackFlow {
        send(Response.Loading())

        try {
            val response = userCollection.find(
                and(
                    User::email eq user.email,
                    User::password eq user.password
                )
            ).awaitFirstOrNull()

            send(Response.Success(response))
        } catch (e: Exception) {
            e.printStackTrace()
            send(Response.Error(e))
        }

        awaitClose {
            launch { send(Response.Error(Exception("Occurred an unknown error."))) }
        }
    }

    override fun signUpUser(user: User): Flow<Response<Boolean>> = callbackFlow {
        send(Response.Loading())

        try {
            val response = userCollection.insertOne(user).awaitFirst()
            send(Response.Success(response.wasAcknowledged()))
        } catch (e: Exception) {
            e.printStackTrace()
            send(Response.Error(e))
        }

        awaitClose {
            launch { send(Response.Error(Exception("Occurred an unknown error."))) }
        }

    }

    override fun isUserExists(email: String): Flow<Response<Boolean>> = callbackFlow {
        send(Response.Loading())

        try {
            val response = userCollection.find(and(User::email eq email)).awaitFirstOrNull()
            send(Response.Success(response != null))
        } catch (e: Exception) {
            e.printStackTrace()
            send(Response.Error(e))
        }

        awaitClose {
            launch { send(Response.Error(Exception("Occurred an unknown error."))) }
        }
    }
}