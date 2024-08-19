package org.fa.blogmultiplatform.exceptions

actual data class FlowClosedException(actual override val message: String = "An unexpected error occurred while sending a request to the service."): Exception(message)