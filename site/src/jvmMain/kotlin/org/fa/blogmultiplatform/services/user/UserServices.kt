package org.fa.blogmultiplatform.services.user

import com.varabyte.kobweb.api.Api
import com.varabyte.kobweb.api.ApiContext
import com.varabyte.kobweb.api.data.getValue
import com.varabyte.kobweb.api.http.HttpMethod
import com.varabyte.kobweb.api.http.setBodyText
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.fa.blogmultiplatform.models.User
import org.fa.blogmultiplatform.repositories.user.UserRepoImpl
import org.fa.blogmultiplatform.util.Response

@Api("create-user")
fun createUser(context: ApiContext) {
    if (context.req.method == HttpMethod.POST) {
        val userRepo = context.data.getValue(UserRepoImpl::class.java)
        val req = context.req.body?.let {
            Json.decodeFromString<User>(it.decodeToString())
        } ?: run {
            val message = "Your request should not be a null."

            context.logger.error(message)
            context.res.status = 400
            context.res.setBodyText(Json.encodeToString(Response(
                isSuccess = false,
                status = 400,
                data = null,
                errorMessages = listOf(message)
            )))

            throw IllegalArgumentException(message)
        }
        userRepo.createUser(req)
        context.res.status = 200
        context.res.setBodyText(Json.encodeToString(Response(
            isSuccess = true,
            status = 200,
            data = true,
            errorMessages = null
        )))
    } else {
        val message = "${context.req.method.name} is not supported."

        context.logger.error(message)
        context.res.setBodyText(Json.encodeToString(Response(
            isSuccess = false,
            status = 405,
            data = null,
            errorMessages = listOf(message)
        )))
    }
}