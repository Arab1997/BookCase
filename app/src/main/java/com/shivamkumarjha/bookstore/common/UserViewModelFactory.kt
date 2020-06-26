package com.shivamkumarjha.bookstore.common

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.shivamkumarjha.bookstore.repository.UserRepository
import com.shivamkumarjha.bookstore.ui.address.AddressViewModel
import com.shivamkumarjha.bookstore.ui.cartitems.CartViewModel
import com.shivamkumarjha.bookstore.ui.delivery.DeliveryViewModel
import com.shivamkumarjha.bookstore.ui.login.LoginViewModel
import com.shivamkumarjha.bookstore.ui.profile.ProfileViewModel
import com.shivamkumarjha.bookstore.ui.register.RegisterViewModel
import com.shivamkumarjha.bookstore.ui.wishitems.WishItemsViewModel

class UserViewModelFactory(private val userRepository: UserRepository) :
    ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AddressViewModel::class.java))
            return AddressViewModel(userRepository) as T
        if (modelClass.isAssignableFrom(CartViewModel::class.java))
            return CartViewModel(userRepository) as T
        if (modelClass.isAssignableFrom(DeliveryViewModel::class.java))
            return DeliveryViewModel(userRepository) as T
        if (modelClass.isAssignableFrom(LoginViewModel::class.java))
            return LoginViewModel(userRepository) as T
        if (modelClass.isAssignableFrom(ProfileViewModel::class.java))
            return ProfileViewModel(userRepository) as T
        if (modelClass.isAssignableFrom(RegisterViewModel::class.java))
            return RegisterViewModel(userRepository) as T
        if (modelClass.isAssignableFrom(WishItemsViewModel::class.java))
            return WishItemsViewModel(userRepository) as T
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
