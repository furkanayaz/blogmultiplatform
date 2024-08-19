package org.fa.blogmultiplatform.exceptions

actual data class FlowClosedException(actual override val message: String = "An error occurred while retrieving data from the service."): Exception(message)
