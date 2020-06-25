package com.shivamkumarjha.bookstore.ui.cart.adapter

import com.shivamkumarjha.bookstore.model.CartItem

interface CartItemClickListener {
    fun onAddQuantity(cartItem: CartItem)
    fun onMinusQuantity(cartItem: CartItem, position: Int)
}