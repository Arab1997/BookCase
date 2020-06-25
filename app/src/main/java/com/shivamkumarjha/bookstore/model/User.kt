package com.shivamkumarjha.bookstore.model

data class User(
    val userID: Int,
    val name: String,
    val email: String,
    val password: String,
    val addressList: ArrayList<Address>? = null,
    val cartList: ArrayList<Cart>? = null,
    val orderList: ArrayList<Order>? = null
)