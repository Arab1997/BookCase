package com.shivamkumarjha.bookstore.ui.delivery

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.shivamkumarjha.bookstore.model.Address
import com.shivamkumarjha.bookstore.repository.AddressRepository

class DeliveryViewModel(private val addressRepository: AddressRepository) : ViewModel() {
    private var _addressList = MutableLiveData<ArrayList<Address>>()
    fun getAddress(): LiveData<ArrayList<Address>> {
        _addressList.value = addressRepository.getAddress()
        return _addressList
    }
}