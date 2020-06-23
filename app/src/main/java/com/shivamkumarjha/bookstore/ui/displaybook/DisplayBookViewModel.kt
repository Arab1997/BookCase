package com.shivamkumarjha.bookstore.ui.displaybook

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.shivamkumarjha.bookstore.model.Review
import com.shivamkumarjha.bookstore.repository.BookRepository

class DisplayBookViewModel(private val bookRepository: BookRepository, private val position: Int) :
    ViewModel() {

    private val _bookTitle = MutableLiveData<String>().apply {
        value = bookRepository.getBook(position).title
    }
    val bookTitle: LiveData<String> = _bookTitle

    private val _bookAuthor = MutableLiveData<String>().apply {
        value = "By ${bookRepository.getBook(position).author}"
    }
    val bookAuthor: LiveData<String> = _bookAuthor

    private val _bookDescription = MutableLiveData<String>().apply {
        value = bookRepository.getBook(position).description
    }
    val bookDescription: LiveData<String> = _bookDescription

    private val _bookPrice = MutableLiveData<String>().apply {
        value = "Rs " + bookRepository.getBook(position).price * 76.25f // Price USD to INR
    }
    val bookPrice: LiveData<String> = _bookPrice

    private val _bookMRP = MutableLiveData<String>().apply {
        value =
            "Rs " + bookRepository.getBook(position).maximumRetailPrice * 76.25f // Price USD to INR
    }
    val bookMRP: LiveData<String> = _bookMRP

    private val _bookDiscount = MutableLiveData<String>().apply {
        value = bookRepository.getBook(position).discount.toInt().toString() + "% off"
    }
    val bookDiscount: LiveData<String> = _bookDiscount

    private val _bookCategory = MutableLiveData<String>().apply {
        value = bookRepository.getBook(position).category
    }
    val bookCategory: LiveData<String> = _bookCategory

    private val _bookDetail = MutableLiveData<String>().apply {
        value = bookRepository.getBook(position).detail
    }
    val bookDetail: LiveData<String> = _bookDetail

    private val _bookRating = MutableLiveData<String>().apply {
        value = "%.2f".format(bookRepository.getBook(position).review.map { it.rating }
            .average()) // Average rating
    }
    val bookRating: LiveData<String> = _bookRating

    private val _bookRatingCount = MutableLiveData<String>().apply {
        value = "${bookRepository.getBook(position).review.size} reviews"
    }
    val bookRatingCount: LiveData<String> = _bookRatingCount

    private var _bookReview = MutableLiveData<ArrayList<Review>>().apply {
        value = bookRepository.getBook(position).review
    }
    val bookReview: LiveData<ArrayList<Review>> = _bookReview

    private var _imageLink = MutableLiveData<ArrayList<String>>().apply {
        value = bookRepository.getBook(position).imageLink
    }
    val imageLink: LiveData<ArrayList<String>> = _imageLink
}