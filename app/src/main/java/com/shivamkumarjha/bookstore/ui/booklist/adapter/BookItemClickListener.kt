package com.shivamkumarjha.bookstore.ui.booklist.adapter

import com.shivamkumarjha.bookstore.model.Book

interface BookItemClickListener {
    fun onBookClick(book: Book)
    fun onWishClick(book: Book, isChecked: Boolean)
}