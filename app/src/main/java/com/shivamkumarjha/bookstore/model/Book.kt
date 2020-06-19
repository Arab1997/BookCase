package com.shivamkumarjha.bookstore.model

import android.widget.ImageView

data class Book(
    val bookID: Int,
    val title: String,
    val author: String,
    val description: String,
    val displayImage: ImageView,
    val image: ImageView,
    val category: String,
    val rating: String,
    val review: String,
    val price: Int,
    val stock: Int
)

