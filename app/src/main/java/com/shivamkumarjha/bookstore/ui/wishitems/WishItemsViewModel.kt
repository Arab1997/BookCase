package com.shivamkumarjha.bookstore.ui.wishitems

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.shivamkumarjha.bookstore.model.WishItem
import com.shivamkumarjha.bookstore.repository.UserRepository

class WishItemsViewModel(private val userRepository: UserRepository) : ViewModel() {

    private var _addressList = MutableLiveData<ArrayList<WishItem>>()
    fun getAddress(): LiveData<ArrayList<WishItem>> {
        _addressList.value = userRepository.getWishItems()
        return _addressList
    }
}