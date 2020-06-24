package com.shivamkumarjha.bookstore.ui.delivery.adapter

import android.annotation.SuppressLint
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.shivamkumarjha.bookstore.R
import com.shivamkumarjha.bookstore.model.Address

class DeliveryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val addressMobile: TextView = itemView.findViewById(R.id.address_item_mobile_id)
    private val addressFlat: TextView = itemView.findViewById(R.id.address_item_flat_id)
    private val addressStreet: TextView = itemView.findViewById(R.id.address_item_street_id)
    private val addressCity: TextView = itemView.findViewById(R.id.address_item_city_id)
    private val addressState: TextView = itemView.findViewById(R.id.address_item_state_id)
    private val addressPinCode: TextView = itemView.findViewById(R.id.address_item_pinCode_id)

    @SuppressLint("SetTextI18n")
    fun initialize(address: Address) {
        addressMobile.text = "Mobile: " + address.mobile
        addressFlat.text = "Flat: " + address.flat
        addressStreet.text = "Street: " + address.street
        addressCity.text = "City: " + address.city
        addressState.text = "State: " + address.state
        addressPinCode.text = "Pin code: " + address.pinCode
    }
}
