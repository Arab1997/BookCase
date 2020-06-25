package com.shivamkumarjha.bookstore.model

data class Order(
    val orderId: Int,
    val cartItem: ArrayList<CartItem>,
    val address: Address,
    val timestamp: String
)