package com.shivamkumarjha.bookstore.ui.booklist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.shivamkumarjha.bookstore.model.Book
import com.shivamkumarjha.bookstore.utility.JsonUtility

class BookViewModel(private val jsonUtility: JsonUtility) : ViewModel() {
    private var books: MutableLiveData<ArrayList<Book>> = MutableLiveData()

    fun getBooks(): LiveData<ArrayList<Book>> {
        books.value = jsonUtility.getBooks()
        return books
    }
}