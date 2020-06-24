package com.shivamkumarjha.bookstore.ui.cart

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.shivamkumarjha.bookstore.model.Cart
import com.shivamkumarjha.bookstore.repository.CartRepository

class CartViewModel(private val cartRepository: CartRepository) : ViewModel() {

    private var _cartList = MutableLiveData<ArrayList<Cart>>()
    fun getCart(): LiveData<ArrayList<Cart>> {
        _cartList.value = cartRepository.getCart()
        return _cartList
    }
}