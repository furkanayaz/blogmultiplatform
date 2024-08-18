package org.fa.blogmultiplatform.util

expect class ResponseDTO<T> {
    val isSuccess: Boolean
    val status: Int
    val data: T?
    val errorMessages: List<String>?
}