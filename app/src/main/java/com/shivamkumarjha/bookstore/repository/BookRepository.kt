package com.shivamkumarjha.bookstore.repository

import android.util.Log
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.shivamkumarjha.bookstore.model.Book
import com.shivamkumarjha.bookstore.model.Review
import java.io.File
import java.io.IOException
import java.io.OutputStreamWriter

class BookRepository(private val file: File) {
    private val gson = Gson()
    private val commonFileRepository = CommonFileRepository(file)
    private val tag = "BookRepository"

    private fun writeBooks(books: ArrayList<Book>) {
        val data = gson.toJson(books)
        try {
            val outputStreamWriter = OutputStreamWriter(file.outputStream())
            outputStreamWriter.write(data)
            outputStreamWriter.close()
        } catch (e: IOException) {
            Log.e(tag, "File write failed: $e")
        }
    }

    fun getBooks(): ArrayList<Book> {
        if (!commonFileRepository.fileExists()) {
            // Create books JSON
            val booksGenerator = BooksGenerator()
            booksGenerator.populate()
            writeBooks(booksGenerator.getBooks())
        }
        // read JSON file storing array of Details object
        val jsonString = commonFileRepository.readFromFile()
        val detailsTypeToken = object : TypeToken<List<Book>>() {}.type
        return gson.fromJson(jsonString, detailsTypeToken)
    }

    private fun getBookIndex(book: Book, books: ArrayList<Book>): Int {
        for (index in 0 until books.size) {
            if (books[index].bookID == book.bookID)
                return index
        }
        return -1
    }

    fun addBookReview(book: Book, review: Review) {
        val books = getBooks()
        val index = getBookIndex(book, books)
        val reviews = books[index].review
        reviews.add(review)
        val newBook = Book(
            book.bookID,
            book.title,
            book.author,
            book.description,
            book.detail,
            book.imageLink,
            book.category,
            reviews,
            book.price,
            book.maximumRetailPrice,
            book.discount
        )
        books[index] = newBook
        writeBooks(books)
    }
}