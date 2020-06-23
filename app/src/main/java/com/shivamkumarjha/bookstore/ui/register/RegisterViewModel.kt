package com.shivamkumarjha.bookstore.ui.register

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.shivamkumarjha.bookstore.R
import com.shivamkumarjha.bookstore.model.RegisterFormState
import com.shivamkumarjha.bookstore.model.User
import com.shivamkumarjha.bookstore.repository.UserFieldValidator
import com.shivamkumarjha.bookstore.repository.UserRepository

class RegisterViewModel(private val userRepository: UserRepository) : ViewModel() {
    private val userFieldValidator = UserFieldValidator()

    private val _registerForm = MutableLiveData<RegisterFormState>()
    val registerFormState: LiveData<RegisterFormState> = _registerForm

    fun registerDataChanged(name: String, email: String, password: String, verifyPassword: String) {
        if (!userFieldValidator.isNameValid(name)) {
            _registerForm.value =
                RegisterFormState(
                    nameError = R.string.invalid_name, isDataValid = false
                )
        } else if (!userFieldValidator.isEmailValid(email)) {
            _registerForm.value =
                RegisterFormState(
                    emailError = R.string.invalid_email, isDataValid = false
                )
        } else if (!userRepository.isEmailNotRegistered(email)) {
            _registerForm.value =
                RegisterFormState(
                    duplicateEmailError = R.string.invalid_email_registered, isDataValid = false
                )
        } else if (!userFieldValidator.isPasswordValid(password)) {
            _registerForm.value =
                RegisterFormState(
                    passwordError = R.string.invalid_password, isDataValid = false
                )
        } else if (password != verifyPassword) {
            _registerForm.value =
                RegisterFormState(
                    verifyPasswordError = R.string.invalid_password_verify, isDataValid = false
                )
        } else {
            _registerForm.value =
                RegisterFormState(
                    isDataValid = true
                )
        }
    }

    fun onSubmitClick(user: User) {
        userRepository.addUser(user)
    }
}