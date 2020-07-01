package com.shivamkumarjha.bookstore.ui.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.shivamkumarjha.bookstore.model.Address
import com.shivamkumarjha.bookstore.repository.UserRepository

class ProfileViewModel(private val userRepository: UserRepository) : ViewModel() {

    private var _addressList = MutableLiveData<ArrayList<Address>>()
    fun getAddress(): LiveData<ArrayList<Address>> {
        _addressList.value = userRepository.getAddress()
        return _addressList
    }

    fun removeAddress(address: Address) {
        userRepository.removeAddress(address)
    }

    fun getProfilePicture(): String? {
        return userRepository.getProfilePicture()
    }

    fun updateProfilePicture(imagePath: String?) {
        userRepository.updateProfilePicture(imagePath)
    }
}
