package com.shivamkumarjha.bookstore.repository

import android.util.Log
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.shivamkumarjha.bookstore.model.Book
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
            val populateBooks =
                PopulateBooks()
            populateBooks.populate()
            writeBooks(populateBooks.getBooks())
        }
        // read JSON file storing array of Details object
        val jsonString = commonFileRepository.readFromFile()
        val detailsTypeToken = object : TypeToken<List<Book>>() {}.type
        return gson.fromJson(jsonString, detailsTypeToken)
    }

    fun getBook(position: Int): Book {
        return getBooks()[position]
    }
}