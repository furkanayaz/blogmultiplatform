package org.fa.blogmultiplatform.util

import kotlinx.serialization.Serializable

@Serializable
actual data class ResponseDTO<T>(
    actual val isSuccess: Boolean,
    actual val status: Int,
    actual val data: T?,
    actual val errorMessages: List<String>?
)
