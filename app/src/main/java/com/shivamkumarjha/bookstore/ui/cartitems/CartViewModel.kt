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

    fun getCartTotalPrice(cartItems: ArrayList<CartItem>): String {
        var price = 0f
        for (index in 0 until cartItems.size) {
            price += cartItems[index].book.price * cartItems[index].quantity * 76.25f
        }
        return "сум $price"
    }

    fun getCartSavings(cartItems: ArrayList<CartItem>): String {
        var savings = 0f
        for (index in 0 until cartItems.size) {
            val bookTotalPrice = cartItems[index].book.price * 76.25f * cartItems[index].quantity
            val bookTotalMRP =
                cartItems[index].book.maximumRetailPrice * 76.25f * cartItems[index].quantity
            savings += bookTotalMRP - bookTotalPrice
        }
        return "сум $savings сохраненный"
    }

    fun removeCart(cartItem: CartItem) {
        userRepository.removeCartItem(cartItem)
    }

    fun updateCart(cartItem: CartItem) {
        userRepository.updateCartItem(cartItem)
    }
}