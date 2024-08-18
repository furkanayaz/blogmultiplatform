package org.fa.blogmultiplatform.util

sealed class Response<T> {
    class Loading<T>: Response<T>()
    data class Error<T>(val exception: Exception): Response<T>()
    data class Success<T>(val result: T): Response<T>()
}