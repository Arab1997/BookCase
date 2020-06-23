package com.shivamkumarjha.bookstore.ui.booklist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.shivamkumarjha.bookstore.repository.BookRepository

class BookListViewModelFactory(private val bookRepository: BookRepository) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(BookListViewModel::class.java))
            return BookListViewModel(bookRepository) as T
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}