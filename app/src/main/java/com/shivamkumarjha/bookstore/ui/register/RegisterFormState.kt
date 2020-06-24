package com.shivamkumarjha.bookstore.ui.register

data class RegisterFormState(
    val nameError: Int? = null,
    val emailError: Int? = null,
    val duplicateEmailError: Int? = null,
    val passwordError: Int? = null,
    val verifyPasswordError: Int? = null,
    val isDataValid: Boolean
)