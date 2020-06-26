package com.shivamkumarjha.bookstore.ui.wishitems.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.shivamkumarjha.bookstore.R
import com.shivamkumarjha.bookstore.model.WishItem

class WishItemsAdapter(private val bookClickListener: BookClickListener) : RecyclerView.Adapter<WishItemsViewHolder>() {

    private var wishItems: ArrayList<WishItem> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WishItemsViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.content_wish_item, parent, false)
        return WishItemsViewHolder(itemView,bookClickListener)
    }

    override fun getItemCount(): Int = wishItems.size

    override fun onBindViewHolder(holder: WishItemsViewHolder, position: Int) {
        holder.initialize(wishItems[position])
    }

    fun setWishItems(wishItems: ArrayList<WishItem>) {
        this.wishItems = wishItems
    }
}