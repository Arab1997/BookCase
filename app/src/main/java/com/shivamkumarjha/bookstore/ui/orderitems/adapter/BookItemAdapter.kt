package com.shivamkumarjha.bookstore.ui.orderitems.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.shivamkumarjha.bookstore.R
import com.shivamkumarjha.bookstore.model.CartItem
import com.shivamkumarjha.bookstore.ui.wishitems.adapter.BookClickListener

class BookItemAdapter(
    private val cartItem: ArrayList<CartItem>,
    private val clickListener: BookClickListener
) :
    RecyclerView.Adapter<BookItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookItemViewHolder {
        val itemView =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.content_order_book_item, parent, false)
        return BookItemViewHolder(itemView,clickListener)
    }

    override fun getItemCount(): Int = cartItem.size

    override fun onBindViewHolder(holder: BookItemViewHolder, position: Int) {
        holder.initialize(cartItem[position])
    }
}
