package com.shivamkumarjha.bookstore.ui.address

data class AddressFormState(
    val mobileError: Int? = null,
    val flatError: Int? = null,
    val streetError: Int? = null,
    val cityError: Int? = null,
    val stateError: Int? = null,
    val pinError: Int? = null,
    val isDataValid: Boolean
)