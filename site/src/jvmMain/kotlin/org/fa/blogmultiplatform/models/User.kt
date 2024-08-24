package org.fa.blogmultiplatform.models

actual data class SignInUser(
    actual val email: String,
    actual val password: String
)

actual data class SignUpUser(
    actual val fullName: String,
    actual val email: String,
    actual val password: String
)