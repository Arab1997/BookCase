package com.shivamkumarjha.bookstore.ui.cart

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.shivamkumarjha.bookstore.model.Cart
import com.shivamkumarjha.bookstore.repository.UserRepository

class CartViewModel(private val userRepository: UserRepository) : ViewModel() {

    private var _cartList = MutableLiveData<ArrayList<Cart>>()
    fun getCart(): LiveData<ArrayList<Cart>> {
        _cartList.value = userRepository.getCart()
        return _cartList
    }

    fun removeCart(cart: Cart) {
        userRepository.removeCart(cart)
    }

    fun updateCart(cart: Cart) {
        userRepository.updateCart(cart)
    }
}