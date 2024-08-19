package org.fa.blogmultiplatform.util

actual sealed class State<T> {
    actual class Loading<T>: State<T>()
    actual data class Error<T>(val exception: Exception): State<T>()
    actual data class Success<T>(val result: T): State<T>()
}