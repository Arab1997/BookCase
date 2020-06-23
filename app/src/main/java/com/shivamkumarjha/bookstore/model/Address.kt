package com.shivamkumarjha.bookstore.model

data class Address(
    val mobile: Int,
    val flat: String,
    val street: String,
    val city: String,
    val state: String,
    val pinCode: Int
)