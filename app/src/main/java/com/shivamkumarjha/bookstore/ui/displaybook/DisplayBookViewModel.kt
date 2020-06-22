package com.shivamkumarjha.bookstore.ui.displaybook

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.shivamkumarjha.bookstore.model.Book
import com.shivamkumarjha.bookstore.model.Review

class DisplayBookViewModel(private val book: Book) : ViewModel() {

    private val _bookTitle = MutableLiveData<String>().apply {
        value = book.title
    }
    val bookTitle: LiveData<String> = _bookTitle

    private val _bookAuthor = MutableLiveData<String>().apply {
        value = "By ${book.author}"
    }
    val bookAuthor: LiveData<String> = _bookAuthor

    private val _bookDescription = MutableLiveData<String>().apply {
        value = book.description
    }
    val bookDescription: LiveData<String> = _bookDescription

    private val _bookPrice = MutableLiveData<String>().apply {
        value = "Rs " + book.price * 76.25f // Price USD to INR
    }
    val bookPrice: LiveData<String> = _bookPrice

    private val _bookMRP = MutableLiveData<String>().apply {
        value = "Rs " + book.maximumRetailPrice * 76.25f // Price USD to INR
    }
    val bookMRP: LiveData<String> = _bookMRP

    private val _bookDiscount = MutableLiveData<String>().apply {
        value = book.discount.toInt().toString() + "% off"
    }
    val bookDiscount: LiveData<String> = _bookDiscount

    private val _bookCategory = MutableLiveData<String>().apply {
        value = book.category
    }
    val bookCategory: LiveData<String> = _bookCategory

    private val _bookDetail = MutableLiveData<String>().apply {
        value = book.detail
    }
    val bookDetail: LiveData<String> = _bookDetail

    private val _bookRating = MutableLiveData<String>().apply {
        value = "%.2f".format(book.review.map { it.rating }.average()) // Average rating
    }
    val bookRating: LiveData<String> = _bookRating

    private val _bookRatingCount = MutableLiveData<String>().apply {
        value = "${book.review.size} reviews"
    }
    val bookRatingCount: LiveData<String> = _bookRatingCount

    private var _bookReview = MutableLiveData<ArrayList<Review>>().apply {
        value = book.review
    }
    val bookReview: LiveData<ArrayList<Review>> = _bookReview

    private var _imageLink = MutableLiveData<ArrayList<String>>().apply {
        value = book.imageLink
    }
    val imageLink: LiveData<ArrayList<String>> = _imageLink

    private val _wishStatus = MutableLiveData<Boolean>().apply {
        value = book.inWishList
    }
    val wishStatus: LiveData<Boolean> = _wishStatus
}