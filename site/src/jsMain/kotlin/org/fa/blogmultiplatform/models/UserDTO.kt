package org.fa.blogmultiplatform.models

import kotlinx.serialization.Serializable

@Serializable
actual data class SignInUserDTO(
    actual val email: String,
    actual val password: String
)

@Serializable
actual data class SignUpUserDTO(
    actual val id: String = "",
    actual val fullName: String,
    actual val email: String,
    actual val password: String
)