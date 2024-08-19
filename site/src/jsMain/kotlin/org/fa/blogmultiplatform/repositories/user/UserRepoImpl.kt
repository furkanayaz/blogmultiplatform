package org.fa.blogmultiplatform.repositories.user

import com.varabyte.kobweb.browser.api
import kotlinx.browser.window
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.launch
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.fa.blogmultiplatform.exceptions.UserValidator
import org.fa.blogmultiplatform.models.SignInUserDTO
import org.fa.blogmultiplatform.models.SignUpUserDTO
import org.fa.blogmultiplatform.util.ResponseDTO
import org.fa.blogmultiplatform.util.State

class UserRepoImpl: UserRepo {
    override fun signIn(req: SignInUserDTO): Flow<State<Boolean>> = callbackFlow {
        send(State.Loading())

        try {
            UserValidator.validate(null, req.email, req.password)

            val responseAsByteArray = window.api.post(apiPath = "sign-in", body = Json.encodeToString(req).encodeToByteArray())
            val response = Json.decodeFromString<ResponseDTO<Boolean>>(responseAsByteArray.decodeToString())

            if (response.isSuccess) {
                send(State.Success(response.data != null))
            } else {
                send(State.Error(Exception(response.errorMessages?.joinToString())))
            }
        } catch (e: Exception) {
            e.printStackTrace()
            send(State.Error(e))
        }

        awaitClose {
            launch { send(State.Error(Exception("Occurred an unknown error."))) }
        }
    }

    override fun signUp(req: SignUpUserDTO): Flow<State<Boolean>> = callbackFlow {
        send(State.Loading())

        try {
            UserValidator.validate(req.fullName, req.email, req.password)

            val responseAsByteArray = window.api.post(apiPath = "sign-up", body = Json.encodeToString(req).encodeToByteArray())
            val response = Json.decodeFromString<ResponseDTO<Boolean>>(responseAsByteArray.decodeToString())

            if (response.isSuccess) {
                send(State.Success(response.data ?: false))
            } else {
                send(State.Error(Exception(message = response.errorMessages?.joinToString())))
            }
        } catch (e: Exception) {
            e.printStackTrace()
            send(State.Error(e))
        }

        awaitClose {
            launch { send(State.Error(Exception("Occurred an unknown error."))) }
        }
    }

    override fun isExist(email: String): Flow<State<Boolean>> = callbackFlow {

    }
}