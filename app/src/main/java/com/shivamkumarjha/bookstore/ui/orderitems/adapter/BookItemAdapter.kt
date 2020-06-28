package com.shivamkumarjha.bookstore.ui.orderitems.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.shivamkumarjha.bookstore.R
import com.shivamkumarjha.bookstore.model.CartItem

class BookItemAdapter(private val cartItem: ArrayList<CartItem>) :
    RecyclerView.Adapter<BookItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookItemViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.content_order_book_item, parent, false)
        return BookItemViewHolder(itemView)
    }

    override fun getItemCount(): Int = cartItem.size

    override fun onBindViewHolder(holder: BookItemViewHolder, position: Int) {
        holder.initialize(cartItem[position])
    }
}
