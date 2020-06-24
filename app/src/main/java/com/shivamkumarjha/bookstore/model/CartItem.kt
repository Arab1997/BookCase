package com.shivamkumarjha.bookstore.model

data class CartItem(
    val itemId: Int,
    val book: Book,
    val quantity: Int
)