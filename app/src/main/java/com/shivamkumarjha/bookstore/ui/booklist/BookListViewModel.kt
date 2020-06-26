package com.shivamkumarjha.bookstore.ui.booklist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.shivamkumarjha.bookstore.model.Book
import com.shivamkumarjha.bookstore.model.WishItem
import com.shivamkumarjha.bookstore.repository.BookRepository
import com.shivamkumarjha.bookstore.repository.UserRepository

class BookListViewModel(
    private val bookRepository: BookRepository
    , private val userRepository: UserRepository
) : ViewModel() {
    private var _getBooks = MutableLiveData<ArrayList<Book>>()
    fun getBooks(): LiveData<ArrayList<Book>> {
        _getBooks.value = bookRepository.getBooks()
        return _getBooks
    }

    private var _getWishItems = MutableLiveData<ArrayList<WishItem>>()
    fun getWishItems(): LiveData<ArrayList<WishItem>> {
        _getWishItems.value = userRepository.getWishItems()
        return _getWishItems
    }

    fun addWishItem(wishItem: WishItem) {
        userRepository.addWishItem(wishItem)
    }

    fun removeWishItem(book: Book) {
        userRepository.removeWishItem(book)
    }
}