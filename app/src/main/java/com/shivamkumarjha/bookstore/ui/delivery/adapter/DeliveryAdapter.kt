package com.shivamkumarjha.bookstore.ui.delivery.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.shivamkumarjha.bookstore.R
import com.shivamkumarjha.bookstore.model.Address

class DeliveryAdapter(
    private var address: ArrayList<Address>,
    private val clickListener: DeliveryItemClickListener
) :
    RecyclerView.Adapter<DeliveryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DeliveryViewHolder {
        val itemView =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.content_delivery_item, parent, false)
        return DeliveryViewHolder(itemView,clickListener)
    }

    override fun getItemCount(): Int = address.size

    override fun onBindViewHolder(holder: DeliveryViewHolder, position: Int) {
        holder.initialize(address[position])
    }
}