package com.shivamkumarjha.bookstore.ui.cart.adapter

import com.shivamkumarjha.bookstore.model.Cart

interface CartItemClickListener {
    fun onAddQuantity(cart: Cart)
    fun onMinusQuantity(cart: Cart, position: Int)
}