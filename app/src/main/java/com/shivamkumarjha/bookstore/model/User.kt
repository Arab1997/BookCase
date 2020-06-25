package com.shivamkumarjha.bookstore.model

data class User(
    val userID: Int,
    val name: String,
    val email: String,
    val password: String,
    val addressList: ArrayList<Address> = arrayListOf(),
    val cartItemList: ArrayList<CartItem> = arrayListOf(),
    val orderList: ArrayList<Order> = arrayListOf()
)