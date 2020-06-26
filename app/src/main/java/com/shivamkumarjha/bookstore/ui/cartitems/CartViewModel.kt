package com.shivamkumarjha.bookstore.ui.cartitems

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.shivamkumarjha.bookstore.model.CartItem
import com.shivamkumarjha.bookstore.repository.UserRepository

class CartViewModel(private val userRepository: UserRepository) : ViewModel() {

    private var _cartList = MutableLiveData<ArrayList<CartItem>>()
    fun getCart(): LiveData<ArrayList<CartItem>> {
        _cartList.value = userRepository.getCartItems()
        return _cartList
    }

    fun removeCart(cartItem: CartItem) {
        userRepository.removeCartItem(cartItem)
    }

    fun updateCart(cartItem: CartItem) {
        userRepository.updateCartItem(cartItem)
    }
}