package com.shivamkumarjha.bookstore.model

data class Address(
    val addressId: Int?,
    val mobile: String,
    val flat: String,
    val street: String,
    val city: String,
    val state: String,
    val pinCode: String
)