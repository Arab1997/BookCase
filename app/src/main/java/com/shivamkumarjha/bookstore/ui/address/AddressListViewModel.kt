package com.shivamkumarjha.bookstore.ui.address

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.shivamkumarjha.bookstore.model.Address
import com.shivamkumarjha.bookstore.repository.AddressRepository

class AddressListViewModel(private val addressRepository: AddressRepository) : ViewModel() {
    private var _getAddress = MutableLiveData<ArrayList<Address>>().apply {
        value = addressRepository.getAddress()
    }
    val getAddress: LiveData<ArrayList<Address>> = _getAddress
}
