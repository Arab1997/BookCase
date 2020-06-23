package com.shivamkumarjha.bookstore.model

data class LoginResult(
    val success: LoggedInUserView? = null,
    val error: Int? = null
)