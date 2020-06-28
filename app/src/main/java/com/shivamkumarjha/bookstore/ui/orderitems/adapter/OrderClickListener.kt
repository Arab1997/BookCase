package com.shivamkumarjha.bookstore.ui.orderitems.adapter

import com.shivamkumarjha.bookstore.model.Book

interface OrderClickListener {
    fun onBookClick(book: Book)
    fun onBookReviewClick(book: Book)
}