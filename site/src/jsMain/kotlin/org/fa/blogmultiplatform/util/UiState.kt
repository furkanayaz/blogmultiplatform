package org.fa.blogmultiplatform.util

sealed class UiState<T> {
    class Idle<T>: UiState<T>()
    class Loading<T>: UiState<T>()
    data class Error<T>(val exception: Exception): UiState<T>()
    data class Success<T>(val result: T): UiState<T>()
}