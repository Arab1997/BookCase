package com.shivamkumarjha.bookstore.ui.profile.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.shivamkumarjha.bookstore.R
import com.shivamkumarjha.bookstore.model.Address

class AddressAdapter(private var addresss: ArrayList<Address>) :
    RecyclerView.Adapter<AddressViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AddressViewHolder {
        val itemView =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.content_address_item, parent, false)
        return AddressViewHolder(itemView)
    }

    override fun getItemCount(): Int = addresss.size

    override fun onBindViewHolder(holder: AddressViewHolder, position: Int) {
        holder.initialize(addresss[position])
    }
}
