package org.fa.blogmultiplatform.models

import kotlinx.serialization.Serializable

@Serializable
actual data class User(
    actual val id: String = "",
    actual val email: String,
    actual val password: String
)