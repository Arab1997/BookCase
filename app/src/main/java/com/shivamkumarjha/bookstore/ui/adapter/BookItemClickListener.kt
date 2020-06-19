package com.shivamkumarjha.bookstore.ui.adapter

import com.shivamkumarjha.bookstore.model.Book

interface BookItemClickListener {
    fun onBookClick(book: Book)
}