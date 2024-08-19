package org.fa.blogmultiplatform.api

import com.varabyte.kobweb.api.Api
import com.varabyte.kobweb.api.ApiContext
import com.varabyte.kobweb.api.data.getValue
import com.varabyte.kobweb.api.http.HttpMethod
import com.varabyte.kobweb.api.http.setBodyText
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.fa.blogmultiplatform.models.SignInUserDTO
import org.fa.blogmultiplatform.models.SignUpUserDTO
import org.fa.blogmultiplatform.repositories.user.UserRepoImpl
import org.fa.blogmultiplatform.util.ResponseDTO
import org.fa.blogmultiplatform.util.State

@Api("sign-in")
suspend fun signIn(context: ApiContext) {
    if (context.req.method == HttpMethod.POST) {
        val userRepo = context.data.getValue(UserRepoImpl::class.java)
        val req = context.req.body?.let {
            Json.decodeFromString<SignInUserDTO>(it.decodeToString())
        } ?: run {
            val message = "Your request should not be a null."

            context.logger.error(message)
            context.res.status = 400
            context.res.setBodyText(
                Json.encodeToString(
                    ResponseDTO(
                        isSuccess = false, status = 400, data = null, errorMessages = listOf(message)
                    )
                )
            )

            throw IllegalArgumentException(message)
        }
        val response = userRepo.signIn(req)
        response.collect {
            when (it) {
                is State.Error -> {
                    context.res.status = 404
                    context.res.setBodyText(
                        Json.encodeToString(
                            ResponseDTO(
                                isSuccess = true,
                                status = 200,
                                data = false,
                                errorMessages = listOf(it.exception.message ?: "null error message")
                            )
                        )
                    )
                }

                is State.Success -> {
                    context.res.status = 200
                    context.res.setBodyText(
                        Json.encodeToString(
                            ResponseDTO(
                                isSuccess = true, status = 200, data = true, errorMessages = null
                            )
                        )
                    )
                }

                else -> { /* NO-OP */
                }
            }
        }
    } else {
        val message = "${context.req.method.name} is not supported."

        context.logger.error(message)
        context.res.setBodyText(
            Json.encodeToString(
                ResponseDTO(
                    isSuccess = false, status = 405, data = null, errorMessages = listOf(message)
                )
            )
        )
    }
}

@Api("sign-up")
suspend fun signUp(context: ApiContext) {
    if (context.req.method == HttpMethod.POST) {
        val userRepo = context.data.getValue(UserRepoImpl::class.java)
        val req = context.req.body?.let {
            Json.decodeFromString<SignUpUserDTO>(it.decodeToString())
        } ?: run {
            val message = "Your request should not be a null."

            context.logger.error(message)
            context.res.status = 400
            context.res.setBodyText(
                Json.encodeToString(
                    ResponseDTO(
                        isSuccess = false, status = 400, data = null, errorMessages = listOf(message)
                    )
                )
            )

            throw IllegalArgumentException(message)
        }
        val response = userRepo.signUp(req)

        response.collect {
            when (it) {
                is State.Error -> {
                    context.res.status = 404
                    context.res.setBodyText(
                        Json.encodeToString(
                            ResponseDTO(
                                isSuccess = true, status = 200, data = false, errorMessages = null
                            )
                        )
                    )
                }

                is State.Success -> {
                    context.res.status = 200
                    context.res.setBodyText(
                        Json.encodeToString(
                            ResponseDTO(
                                isSuccess = true, status = 200, data = true, errorMessages = null
                            )
                        )
                    )
                }

                else -> { /* NO-OP */
                }
            }
        }
    } else {
        val message = "${context.req.method.name} is not supported."

        context.logger.error(message)
        context.res.setBodyText(
            Json.encodeToString(
                ResponseDTO(
                    isSuccess = false, status = 405, data = null, errorMessages = listOf(message)
                )
            )
        )
    }
}