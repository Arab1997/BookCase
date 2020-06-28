package com.shivamkumarjha.bookstore.model

data class Book(
    val bookID: Int,
    val title: String,
    val author: String,
    val description: String,
    val detail: String,
    val imageLink: ArrayList<String>,
    val category: String,
    var review: ArrayList<Review>,
    var price: Float,
    var maximumRetailPrice: Float,
    var discount: Float
)

