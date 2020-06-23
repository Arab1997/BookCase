package com.shivamkumarjha.bookstore.ui.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.shivamkumarjha.bookstore.model.LoggedInUserView

class ProfileViewModelFactory(private val loggedInUserView: LoggedInUserView) :
    ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ProfileViewModel::class.java))
            return ProfileViewModel(loggedInUserView) as T
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
