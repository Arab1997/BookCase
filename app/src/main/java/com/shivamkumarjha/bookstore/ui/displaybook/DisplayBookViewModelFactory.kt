package com.shivamkumarjha.bookstore.ui.displaybook

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.shivamkumarjha.bookstore.repository.BookRepository

class DisplayBookViewModelFactory(private val bookRepository: BookRepository, private val position:Int) :
    ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DisplayBookViewModel::class.java))
            return DisplayBookViewModel(bookRepository,position) as T
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
