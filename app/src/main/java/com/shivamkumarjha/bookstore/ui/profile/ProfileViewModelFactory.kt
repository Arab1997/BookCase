package com.shivamkumarjha.bookstore.ui.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.shivamkumarjha.bookstore.repository.AddressRepository
import com.shivamkumarjha.bookstore.repository.ProfileRepository

class ProfileViewModelFactory(
    private val profileRepository: ProfileRepository,
    private val addressRepository: AddressRepository
) :
    ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ProfileViewModel::class.java))
            return ProfileViewModel(profileRepository, addressRepository) as T
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
