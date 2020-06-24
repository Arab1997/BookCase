package com.shivamkumarjha.bookstore.common

import android.content.Context
import android.content.SharedPreferences
import com.shivamkumarjha.bookstore.R

class AppPreference(private val context: Context) {
    private val sharedPreferences: SharedPreferences by lazy {
        context.getSharedPreferences(
            context.resources.getString(R.string.appPreference),
            Context.MODE_PRIVATE
        )
    }

    fun getUserEmail(): String? {
        return sharedPreferences.getString(
            context.resources.getString(R.string.email_preference), null
        )
    }

    fun setUserEmail(email: String) {
        sharedPreferences.edit()
            .putString(context.resources.getString(R.string.email_preference), email).apply()
    }

    fun getUserName(): String? {
        return sharedPreferences.getString(
            context.resources.getString(R.string.name_preference), null
        )
    }

    fun setUserName(email: String) {
        sharedPreferences.edit()
            .putString(context.resources.getString(R.string.name_preference), email).apply()
    }

    fun getSignIn(): Boolean {
        return sharedPreferences.getBoolean(
            context.resources.getString(R.string.logged_preference),
            false
        )
    }

    fun setSignIn(value: Boolean) {
        sharedPreferences.edit()
            .putBoolean(context.resources.getString(R.string.logged_preference), value)
            .apply()
    }

    fun newUserId(): Int {
        val position =
            sharedPreferences.getInt(context.resources.getString(R.string.userId), 0) + 1
        sharedPreferences.edit()
            .putInt(context.resources.getString(R.string.userId), position).apply()
        return position
    }

    fun newAddressId(): Int {
        val position =
            sharedPreferences.getInt(
                context.resources.getString(R.string.address_id_preference),
                0
            ) + 1
        sharedPreferences.edit()
            .putInt(context.resources.getString(R.string.address_id_preference), position).apply()
        return position
    }

    fun newCartId(): Int {
        val position =
            sharedPreferences.getInt(
                context.resources.getString(R.string.cart_id_preference),
                0
            ) + 1
        sharedPreferences.edit()
            .putInt(context.resources.getString(R.string.cart_id_preference), position).apply()
        return position
    }

    fun newOrderId(): Int {
        val position =
            sharedPreferences.getInt(
                context.resources.getString(R.string.order_id_preference),
                0
            ) + 1
        sharedPreferences.edit()
            .putInt(context.resources.getString(R.string.order_id_preference), position).apply()
        return position
    }
}