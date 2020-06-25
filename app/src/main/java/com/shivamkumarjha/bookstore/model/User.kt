package com.shivamkumarjha.bookstore.model

data class User(
    val userID: Int,
    val name: String,
    val email: String,
    val password: String,
    val addresses: ArrayList<Address> = arrayListOf(),
    val cartItems: ArrayList<CartItem> = arrayListOf(),
    val orders: ArrayList<Order> = arrayListOf()
)