package com.shivamkumarjha.bookstore.utility

import com.shivamkumarjha.bookstore.model.Book

class PopulateBooks {

    private var books: ArrayList<Book> = ArrayList()
    private var booksCount = 0

    private fun addBook(book: Book) {
        books.add(book)
        booksCount++
    }

    fun getBooks(): ArrayList<Book> {
        return books
    }

    fun populate() {
        val book1 = Book(
            booksCount,
            "Drifts",
            "Kate Zambreno",
            "A restlessly brilliant novel of creative crisis and transformation",
            "A story of artistic ambition, personal crisis, and the possibilities and " +
                    "failures of literature, Drifts is a dramatic step forward for one of our " +
                    "most daring writers.",
            "https://i.gr-assets.com/images/S/compressed.photo.goodreads.com/books/1578676099l/48585697.jpg",
            "Novel",
            "4.01",
            "Time is a slippery thing. Zambreno reckons with the problematic relationship writers have with time.",
            14,
            10,
            false
        )
        addBook(book1)
    }

}