package com.shivamkumarjha.bookstore.model

data class User(
    val userID: Int,
    val firstName: String,
    val lastName: String,
    val email: String,
    val password: String,
    val address: ArrayList<Address>
)