package com.shivamkumarjha.bookstore.ui.booklist.adapter

import com.shivamkumarjha.bookstore.model.Book

interface BookItemClickListener {
    fun onBookClick(position: Int)
    fun onWishClick(book: Book, isChecked: Boolean)
}