package com.shivamkumarjha.bookstore.ui.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.shivamkumarjha.bookstore.model.LoggedInUserView

class ProfileViewModel(private val loggedInUserView: LoggedInUserView) : ViewModel() {
    private var _getName = MutableLiveData<String>().apply {
        value = loggedInUserView.name
    }
    val getName: LiveData<String> = _getName

    private var _getEmail = MutableLiveData<String>().apply {
        value = loggedInUserView.email
    }
    val getEmail: LiveData<String> = _getEmail
}
