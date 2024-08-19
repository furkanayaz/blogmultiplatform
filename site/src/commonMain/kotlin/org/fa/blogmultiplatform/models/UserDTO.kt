package org.fa.blogmultiplatform.models

expect class SignInUserDTO {
    val email: String
    val password: String
}

expect class SignUpUserDTO {
    val id: String
    val fullName: String
    val email: String
    val password: String
}