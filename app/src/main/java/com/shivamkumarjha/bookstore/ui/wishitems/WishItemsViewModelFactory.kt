package com.shivamkumarjha.bookstore.ui.wishitems

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.shivamkumarjha.bookstore.repository.UserRepository

class WishItemsViewModelFactory(private val userRepository: UserRepository) :
    ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(WishItemsViewModel::class.java))
            return WishItemsViewModel(userRepository) as T
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}