package com.shivamkumarjha.bookstore.model

data class Book(
    val bookID: Int,
    val title: String,
    val author: String,
    val description: String,
    val detail: String,
    val imageLink: String,
    val category: String,
    val rating: Float,
    val review: String,
    val price: Float,
    val stock: Int,
    val inWishList: Boolean
)

