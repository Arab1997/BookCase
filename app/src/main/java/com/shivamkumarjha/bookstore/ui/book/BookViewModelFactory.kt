package com.shivamkumarjha.bookstore.ui.book

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.shivamkumarjha.bookstore.utility.JsonUtility

class BookViewModelFactory(private val jsonUtility: JsonUtility) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(BookViewModel::class.java))
            return BookViewModel(jsonUtility) as T
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}