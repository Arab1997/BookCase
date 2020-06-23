package com.shivamkumarjha.bookstore.repository

import android.util.Patterns

class UserFieldValidator {
    fun isNameValid(name: String): Boolean {
        return name.length > 3
    }

    fun isEmailValid(email: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    fun isPasswordValid(password: String): Boolean {
        return password.length > 5
    }
}