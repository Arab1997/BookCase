package com.shivamkumarjha.bookstore.ui.booklist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.shivamkumarjha.bookstore.model.Book
import com.shivamkumarjha.bookstore.repository.BookRepository

class BookListViewModel(private val bookRepository: BookRepository) : ViewModel() {
    private var _getBooks = MutableLiveData<ArrayList<Book>>().apply {
        value = bookRepository.getBooks()
    }
    val getBooks: LiveData<ArrayList<Book>> = _getBooks
}