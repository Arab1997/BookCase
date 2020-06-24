package com.shivamkumarjha.bookstore.ui.delivery.adapter

import com.shivamkumarjha.bookstore.model.Address

interface DeliveryItemClickListener {
    fun onAddressClick(address: Address)
}