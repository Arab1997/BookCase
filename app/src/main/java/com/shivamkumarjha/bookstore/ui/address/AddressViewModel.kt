package com.shivamkumarjha.bookstore.ui.address

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.shivamkumarjha.bookstore.R
import com.shivamkumarjha.bookstore.model.Address
import com.shivamkumarjha.bookstore.model.AddressFormState
import com.shivamkumarjha.bookstore.repository.AddressRepository
import com.shivamkumarjha.bookstore.repository.AddressValidator

class AddressViewModel(private val addressRepository: AddressRepository) : ViewModel() {

    private val addressValidator = AddressValidator()

    private val _addressForm = MutableLiveData<AddressFormState>()
    val addressFormState: LiveData<AddressFormState> = _addressForm

    fun addressDataChanged(address: Address) {
        if (!addressValidator.isMobileValid(address.mobile)) {
            _addressForm.value = AddressFormState(
                mobileError = R.string.invalid_mobile, isDataValid = false
            )
        } else if (!addressValidator.isAddressValid(address.flat)) {
            _addressForm.value = AddressFormState(
                flatError = R.string.invalid_flat, isDataValid = false
            )
        } else if (!addressValidator.isAddressValid(address.street)) {
            _addressForm.value = AddressFormState(
                streetError = R.string.invalid_street, isDataValid = false
            )
        } else if (!addressValidator.isAddressValid(address.city)) {
            _addressForm.value = AddressFormState(
                cityError = R.string.invalid_city, isDataValid = false
            )
        } else if (!addressValidator.isAddressValid(address.state)) {
            _addressForm.value = AddressFormState(
                stateError = R.string.invalid_state, isDataValid = false
            )
        } else if (!addressValidator.isPinCodeValid(address.pinCode)) {
            _addressForm.value = AddressFormState(
                pinError = R.string.invalid_pinCode, isDataValid = false
            )
        } else {
            _addressForm.value = AddressFormState(
                isDataValid = true
            )
        }
    }

    fun onSubmitClick(address: Address) {
        addressRepository.addAddress(address)
    }
}
