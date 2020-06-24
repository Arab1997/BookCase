package com.shivamkumarjha.bookstore.ui.delivery

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.shivamkumarjha.bookstore.repository.AddressRepository

class DeliveryViewModelFactory(private val addressRepository: AddressRepository) :
    ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DeliveryViewModel::class.java))
            return DeliveryViewModel(addressRepository) as T
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
