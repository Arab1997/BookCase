package com.shivamkumarjha.bookstore.ui.wishitems.adapter

import com.shivamkumarjha.bookstore.model.Book

interface BookClickListener {
    fun onBookClick(book: Book)
}