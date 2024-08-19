package org.fa.blogmultiplatform.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.litote.kmongo.id.ObjectIdGenerator

@Serializable
actual data class SignInUserDTO(
    actual val email: String,
    actual val password: String
)

@Serializable
actual data class SignUpUserDTO(
    @SerialName("_id")
    actual val id: String = ObjectIdGenerator.newObjectId<String>().id.toHexString(),
    actual val fullName: String,
    actual val email: String,
    actual val password: String
)
