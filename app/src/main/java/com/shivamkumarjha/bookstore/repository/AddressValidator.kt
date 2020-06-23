package com.shivamkumarjha.bookstore.repository

class AddressValidator {
    fun isMobileValid(mobile: String): Boolean {
        return mobile.length == 10
    }

    fun isAddressValid(name: String): Boolean {
        return name.length > 3
    }

    fun isPinCodeValid(pinCode: String): Boolean {
        return pinCode.length == 6
    }
}