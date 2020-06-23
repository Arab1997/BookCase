package com.shivamkumarjha.bookstore.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class LoggedInUserView(
    val name: String,
    val email: String
) : Parcelable