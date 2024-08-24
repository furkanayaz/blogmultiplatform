package org.fa.blogmultiplatform.util

class EmailValidator {
    companion object {
        const val EMAIL_REGEX = "^([a-zA-Z0-9._%-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,})\$"
    }

    fun validateEmail(email: String): Boolean {
        val regex = Regex(EMAIL_REGEX)
        return regex.matches(email)
    }
}