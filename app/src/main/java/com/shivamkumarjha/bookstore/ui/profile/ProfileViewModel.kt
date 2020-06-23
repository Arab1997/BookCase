package com.shivamkumarjha.bookstore.ui.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.shivamkumarjha.bookstore.repository.ProfileRepository

class ProfileViewModel(private val profileRepository: ProfileRepository) :
    ViewModel() {
    private var _getName = MutableLiveData<String>().apply {
        value = profileRepository.getLoggedInUser().name
    }
    val getName: LiveData<String> = _getName

    private var _getEmail = MutableLiveData<String>().apply {
        value = profileRepository.getLoggedInUser().email
    }
    val getEmail: LiveData<String> = _getEmail
}
