package org.fa.blogmultiplatform.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.litote.kmongo.id.ObjectIdGenerator

@Serializable
actual data class User(
    @SerialName("_id")
    actual val id: String = ObjectIdGenerator.newObjectId<String>().id.toHexString(),
    actual val email: String,
    actual val password: String
)
