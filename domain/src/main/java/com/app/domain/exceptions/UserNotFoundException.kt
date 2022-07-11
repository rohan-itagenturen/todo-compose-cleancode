package com.app.domain.exceptions

data class LoginFailureException(override val message: String) : Exception(message)