package com.shivamkumarjha.bookstore.ui.profile.adapter

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.shivamkumarjha.bookstore.R
import com.shivamkumarjha.bookstore.model.Address

class AddressViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val addressMobile: TextView = itemView.findViewById(R.id.address_item_mobile_id)
    private val addressFlat: TextView = itemView.findViewById(R.id.address_item_flat_id)
    private val addressStreet: TextView = itemView.findViewById(R.id.address_item_street_id)
    private val addressCity: TextView = itemView.findViewById(R.id.address_item_city_id)
    private val addressState: TextView = itemView.findViewById(R.id.address_item_state_id)
    private val addressPinCode: TextView = itemView.findViewById(R.id.address_item_pinCode_id)

    fun initialize(address: Address) {
        addressMobile.text = address.mobile
        addressFlat.text = address.flat
        addressStreet.text = address.street
        addressCity.text = address.city
        addressState.text = address.state
        addressPinCode.text = address.pinCode
    }
}
