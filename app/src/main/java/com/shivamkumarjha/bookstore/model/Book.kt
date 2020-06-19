package com.shivamkumarjha.bookstore.model

data class Book(
    val bookID: Int,
    val title: String,
    val author: String,
    val description: String,
    val rating: String,
    val review: String,
    val price: Int,
    val stock: Int
)

