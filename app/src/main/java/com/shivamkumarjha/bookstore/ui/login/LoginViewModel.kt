package com.shivamkumarjha.bookstore.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.shivamkumarjha.bookstore.R
import com.shivamkumarjha.bookstore.model.LoginFormState
import com.shivamkumarjha.bookstore.repository.UserFieldValidator
import com.shivamkumarjha.bookstore.repository.UserRepository

class LoginViewModel(private val userRepository: UserRepository) : ViewModel() {
    private val _loginForm = MutableLiveData<LoginFormState>()
    val loginFormState: LiveData<LoginFormState> = _loginForm

    private val userFieldValidator = UserFieldValidator()

    fun loginDataChanged(email: String, password: String) {
        if (!userFieldValidator.isEmailValid(email)) {
            _loginForm.value =
                LoginFormState(
                    emailError = R.string.invalid_email, isDataValid = false
                )
        } else if (userRepository.isEmailNotRegistered(password)) {
            _loginForm.value =
                LoginFormState(
                    emailUnregisteredError = R.string.invalid_email_unregistered,
                    isDataValid = false
                )
        } else if (!userFieldValidator.isPasswordValid(password)) {
            _loginForm.value =
                LoginFormState(
                    passwordError = R.string.invalid_password, isDataValid = false
                )
        } else {
            _loginForm.value =
                LoginFormState(
                    isDataValid = true
                )
        }
    }

    fun onLogin(email: String, password: String): Boolean {
        return userRepository.login(email, password)
    }
}