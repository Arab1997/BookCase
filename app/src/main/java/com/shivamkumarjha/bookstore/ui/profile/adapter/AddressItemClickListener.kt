package com.shivamkumarjha.bookstore.ui.profile.adapter

import com.shivamkumarjha.bookstore.model.Address

interface AddressItemClickListener {
    fun onEditClick(address: Address)
    fun onDeleteClick(address: Address, position: Int)
}