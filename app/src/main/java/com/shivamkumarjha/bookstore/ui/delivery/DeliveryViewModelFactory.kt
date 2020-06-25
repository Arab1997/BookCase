package com.shivamkumarjha.bookstore.ui.delivery

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.shivamkumarjha.bookstore.repository.UserRepository

class DeliveryViewModelFactory(private val userRepository: UserRepository) :
    ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DeliveryViewModel::class.java))
            return DeliveryViewModel(userRepository) as T
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
