package com.shivamkumarjha.bookstore.ui.address

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.shivamkumarjha.bookstore.repository.AddressRepository

class AddressViewModelFactory(private val addressRepository: AddressRepository) :
    ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AddressViewModel::class.java))
            return AddressViewModel(addressRepository) as T
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
