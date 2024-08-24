package org.fa.blogmultiplatform.exceptions

import org.fa.blogmultiplatform.util.EmailValidator

sealed class UserValidator(override val message: String): Exception(message) {
    data class FullNameValidationException(override val message: String = "Please enter a valid full-name."): UserValidator(message)
    data class EmailValidationException(override val message: String = "Please enter a valid email address."): UserValidator(message)
    data class PasswordValidationException(override val message: String = "Please enter a valid password."): UserValidator(message)

    companion object {
        fun validate(fullName: String?, email: String, password: String) {
            if (fullName == null)
                throw FullNameValidationException()

            if (fullName.length < 6)
                throw FullNameValidationException()

            if (EmailValidator().validateEmail(email).not())
                throw EmailValidationException()

            if (password.length < 6)
                throw PasswordValidationException()
        }
    }

}