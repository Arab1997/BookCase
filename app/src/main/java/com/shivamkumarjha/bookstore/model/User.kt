package com.shivamkumarjha.bookstore.model

import android.graphics.Bitmap

data class User(
    val userID: Int,
    var name: String,
    var email: String,
    var password: String,
    var addresses: ArrayList<Address> = arrayListOf(),
    var cartItems: ArrayList<CartItem> = arrayListOf(),
    var orders: ArrayList<Order> = arrayListOf(),
    var wishItems: ArrayList<WishItem> = arrayListOf(),
    var userImage: Bitmap? = null
)