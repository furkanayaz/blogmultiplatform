package org.fa.blogmultiplatform.util

data class Response<T>(
    val isSuccess: Boolean,
    val status: Int,
    val data: T?,
    val errorMessages: List<String>?
)
