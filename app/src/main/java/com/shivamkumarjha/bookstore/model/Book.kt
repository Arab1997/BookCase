package com.shivamkumarjha.bookstore.model

data class Book(
    val bookID: Int,
    val title: String,
    val author: String,
    val description: String,
    val detail: String,
    val imageLink: String,
    val category: String,
    val review: ArrayList<Review>,
    val price: Float,
    val maximumRetailPrice: Float,
    val discount: Float,
    val stock: Int,
    val inWishList: Boolean
)

