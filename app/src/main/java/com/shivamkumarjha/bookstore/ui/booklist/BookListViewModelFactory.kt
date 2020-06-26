package com.shivamkumarjha.bookstore.ui.booklist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.shivamkumarjha.bookstore.repository.BookRepository
import com.shivamkumarjha.bookstore.repository.UserRepository

class BookListViewModelFactory(
    private val bookRepository: BookRepository,
    private val userRepository: UserRepository
) :
    ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(BookListViewModel::class.java))
            return BookListViewModel(bookRepository, userRepository) as T
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}