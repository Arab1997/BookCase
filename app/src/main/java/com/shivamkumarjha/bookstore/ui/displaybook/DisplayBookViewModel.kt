package com.shivamkumarjha.bookstore.ui.displaybook

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.shivamkumarjha.bookstore.model.Book
import com.shivamkumarjha.bookstore.model.CartItem
import com.shivamkumarjha.bookstore.model.Review
import com.shivamkumarjha.bookstore.model.WishItem
import com.shivamkumarjha.bookstore.repository.UserRepository

class DisplayBookViewModel(private val book: Book, private val userRepository: UserRepository) :
    ViewModel() {

    private val _bookTitle = MutableLiveData<String>().apply {
        value = book.title
    }
    val bookTitle: LiveData<String> = _bookTitle

    private val _bookAuthor = MutableLiveData<String>().apply {
        value = "Автор ${book.author}"
    }
    val bookAuthor: LiveData<String> = _bookAuthor

    private val _bookDescription = MutableLiveData<String>().apply {
        value = book.description
    }
    val bookDescription: LiveData<String> = _bookDescription

    private val _bookPrice = MutableLiveData<String>().apply {
        value = "сум " + book.price * 3500.5f // Price USD to INR
    }
    val bookPrice: LiveData<String> = _bookPrice

    private val _bookMRP = MutableLiveData<String>().apply {
        value = "сум " + book.maximumRetailPrice * 4500.5f // Price USD to INR
    }
    val bookMRP: LiveData<String> = _bookMRP

    private val _bookDiscount = MutableLiveData<String>().apply {
        value = book.discount.toInt().toString() + "%"
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
        value = "%.1f".format(book.review.map { it.rating }.average()) // Average rating
    }
    val bookRating: LiveData<String> = _bookRating

    private val _bookRatingCount = MutableLiveData<String>().apply {
        value = "${book.review.size} отзывы"
    }
    val bookRatingCount: LiveData<String> = _bookRatingCount

    private var _bookReview = MutableLiveData<ArrayList<Review>>()
    fun getReviews(): LiveData<ArrayList<Review>> {
        _bookReview.value = book.review
        return _bookReview
    }

    private var _imageLink = MutableLiveData<ArrayList<String>>()
    fun getImages(): LiveData<ArrayList<String>> {
        _imageLink.value = book.imageLink
        return _imageLink
    }

    fun addToCart(cartId: Int) {
        userRepository.addCartItem(
            CartItem(
                cartId,
                book,
                1
            )
        )
    }

    fun isBookInCartItems(): Boolean {
        return userRepository.isBookInCartItems(book)
    }

    fun isBookInWishItems(): Boolean {
        return userRepository.isBookInWishItems(book)
    }

    fun addWishItem(wishItem: WishItem) {
        userRepository.addWishItem(wishItem)
    }

    fun removeWishItem(book: Book) {
        userRepository.removeWishItem(book)
    }
}