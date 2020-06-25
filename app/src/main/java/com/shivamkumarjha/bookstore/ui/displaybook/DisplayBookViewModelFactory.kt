package com.shivamkumarjha.bookstore.ui.displaybook

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.shivamkumarjha.bookstore.model.Book
import com.shivamkumarjha.bookstore.repository.UserRepository

class DisplayBookViewModelFactory(
    private val book: Book,
    private val userRepository: UserRepository
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DisplayBookViewModel::class.java))
            return DisplayBookViewModel(book, userRepository) as T
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
