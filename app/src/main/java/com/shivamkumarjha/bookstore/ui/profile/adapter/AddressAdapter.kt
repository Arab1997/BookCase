package com.shivamkumarjha.bookstore.ui.profile.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.shivamkumarjha.bookstore.R
import com.shivamkumarjha.bookstore.model.Address

class AddressAdapter(private val clickListener: AddressItemClickListener) :
    RecyclerView.Adapter<AddressViewHolder>() {

    private var address: ArrayList<Address> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AddressViewHolder {
        val itemView =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.content_address_item, parent, false)
        return AddressViewHolder(itemView, clickListener)
    }

    override fun getItemCount(): Int = address.size

    override fun onBindViewHolder(holder: AddressViewHolder, position: Int) {
        holder.initialize(address[position], position)
    }

    fun setAddress(address: ArrayList<Address>) {
        this.address = address
        notifyDataSetChanged()
    }

    fun getAddress(): ArrayList<Address> = address
}
