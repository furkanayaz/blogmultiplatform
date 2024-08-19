package org.fa.blogmultiplatform.exceptions

data class EmailValidationException(override val message: String = "Please enter a valid email address."): Exception(message)