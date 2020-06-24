package com.shivamkumarjha.bookstore.ui.delivery.adapter

import android.annotation.SuppressLint
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.shivamkumarjha.bookstore.R
import com.shivamkumarjha.bookstore.model.Address

class DeliveryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val deliveryMobile: TextView = itemView.findViewById(R.id.delivery_item_mobile_id)
    private val deliveryFlat: TextView = itemView.findViewById(R.id.delivery_item_flat_id)
    private val deliveryStreet: TextView = itemView.findViewById(R.id.delivery_item_street_id)
    private val deliveryCity: TextView = itemView.findViewById(R.id.delivery_item_city_id)
    private val deliveryState: TextView = itemView.findViewById(R.id.delivery_item_state_id)
    private val deliveryPinCode: TextView = itemView.findViewById(R.id.delivery_item_pinCode_id)

    @SuppressLint("SetTextI18n")
    fun initialize(delivery: Address) {
        deliveryMobile.text = "Mobile: " + delivery.mobile
        deliveryFlat.text = "Flat: " + delivery.flat
        deliveryStreet.text = "Street: " + delivery.street
        deliveryCity.text = "City: " + delivery.city
        deliveryState.text = "State: " + delivery.state
        deliveryPinCode.text = "Pin code: " + delivery.pinCode
    }
}
