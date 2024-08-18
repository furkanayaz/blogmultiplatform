package org.fa.blogmultiplatform.services

import com.varabyte.kobweb.browser.api
import kotlinx.browser.window
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.launch
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.fa.blogmultiplatform.models.User
import org.fa.blogmultiplatform.util.ResponseDTO
import org.fa.blogmultiplatform.util.UiState

fun signUp(user: User): Flow<UiState<ResponseDTO<Boolean>>> = callbackFlow {
    send(UiState.Loading())

    try {
        val response = window.api.post(apiPath = "sign-up", body = Json.encodeToString(user).encodeToByteArray())
        send(UiState.Success(Json.decodeFromString<ResponseDTO<Boolean>>(response.decodeToString())))
    } catch (e: Exception) {
        e.printStackTrace()
        send(UiState.Error(e))
    }

    awaitClose {
        launch { send(UiState.Error(Exception("Occurred an unknown error."))) }
    }
}