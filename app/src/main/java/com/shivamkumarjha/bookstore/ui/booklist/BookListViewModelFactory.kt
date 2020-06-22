package com.shivamkumarjha.bookstore.ui.booklist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.shivamkumarjha.bookstore.utility.JsonUtility

class BookListViewModelFactory(private val jsonUtility: JsonUtility) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(BookListViewModel::class.java))
            return BookListViewModel(jsonUtility) as T
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}