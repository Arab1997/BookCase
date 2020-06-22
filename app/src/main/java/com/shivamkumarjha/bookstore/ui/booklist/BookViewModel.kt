package com.shivamkumarjha.bookstore.ui.booklist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.shivamkumarjha.bookstore.model.Book
import com.shivamkumarjha.bookstore.utility.JsonUtility

class BookViewModel(private val jsonUtility: JsonUtility) : ViewModel() {
    private var _getBooks = MutableLiveData<ArrayList<Book>>().apply {
        value = jsonUtility.getBooks()
    }
    val getBooks: LiveData<ArrayList<Book>> = _getBooks
}