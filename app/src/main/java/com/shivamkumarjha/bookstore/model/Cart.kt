package com.shivamkumarjha.bookstore.model

data class Cart(
    val cartId: Int,
    val book: Book,
    val quantity: Int
)