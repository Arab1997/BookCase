package com.shivamkumarjha.bookstore.ui.cartitems.adapter

import com.shivamkumarjha.bookstore.model.Book
import com.shivamkumarjha.bookstore.model.CartItem

interface CartItemClickListener {
    fun onAddQuantity(cartItem: CartItem)
    fun onMinusQuantity(cartItem: CartItem, position: Int)
    fun onBookClick(book: Book)
}