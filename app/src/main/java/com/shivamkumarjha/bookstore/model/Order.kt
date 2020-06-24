package com.shivamkumarjha.bookstore.model

data class Order(
    val orderId: Int,
    val cart: ArrayList<Cart>,
    val address: Address,
    val timestamp: String
)