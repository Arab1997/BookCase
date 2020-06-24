package com.shivamkumarjha.bookstore.model

data class Cart(
    val itemId: Int,
    val book: Book,
    var quantity: Int
)