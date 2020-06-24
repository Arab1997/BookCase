package com.shivamkumarjha.bookstore.ui.login

data class LoginFormState(
    val emailError: Int? = null,
    val emailUnregisteredError: Int? = null,
    val passwordError: Int? = null,
    val isDataValid: Boolean
)