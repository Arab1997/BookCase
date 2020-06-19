package com.shivamkumarjha.bookstore.utility

import com.shivamkumarjha.bookstore.model.Book

class PopulateBooks {

    private lateinit var books: ArrayList<Book>

    private fun addBook(book: Book) {
        books.add(book)
    }

    fun getBooks(): ArrayList<Book> {
        return books
    }

    fun populate() {

    }

}