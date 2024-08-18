package org.fa.blogmultiplatform.util

sealed class State<T> {
    class Loading<T>: State<T>()
    data class Error<T>(val exception: Exception): State<T>()
    data class Success<T>(val result: T): State<T>()
}