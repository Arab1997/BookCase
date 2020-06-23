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
            context.resources.getString(R.string.previous_email_preference), null
        )
    }

    fun setUserEmail(email: String) {
        sharedPreferences.edit()
            .putString(
                context.resources.getString(R.string.previous_email_preference),
                email
            ).apply()
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

    fun getIsNewLogin(): Boolean {
        return sharedPreferences.getBoolean(
            context.resources.getString(R.string.new_login_preference),
            false
        )
    }

    fun setIsNewLogin(value: Boolean) {
        sharedPreferences.edit()
            .putBoolean(context.resources.getString(R.string.new_login_preference), value)
            .apply()
    }

    fun newUserId(): Int {
        val position =
            sharedPreferences.getInt(context.resources.getString(R.string.userId), 0) + 1
        sharedPreferences.edit()
            .putInt(context.resources.getString(R.string.userId), position).apply()
        return position
    }
}