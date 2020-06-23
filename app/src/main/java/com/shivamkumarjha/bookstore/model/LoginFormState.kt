package com.shivamkumarjha.bookstore.model

data class LoginFormState(
    val emailError: Int? = null,
    val emailUnregisteredError: Int? = null,
    val passwordError: Int? = null,
    val isDataValid: Boolean
)