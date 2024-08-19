package org.fa.blogmultiplatform.util

expect sealed class State<T> {
    class Loading<T>: State<T>
    class Error<T>: State<T>
    class Success<T>: State<T>
}