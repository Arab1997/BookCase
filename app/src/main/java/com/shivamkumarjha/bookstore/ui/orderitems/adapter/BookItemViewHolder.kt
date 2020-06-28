package com.shivamkumarjha.bookstore.ui.orderitems.adapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.shivamkumarjha.bookstore.R
import com.shivamkumarjha.bookstore.model.CartItem
import com.squareup.picasso.Picasso

class BookItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val bookTitle: TextView = itemView.findViewById(R.id.order_book_title_text_view_id)
    private val bookImage: ImageView = itemView.findViewById(R.id.order_book_image_text_view_id)

    fun initialize(cartItem: CartItem) {
        bookTitle.text = cartItem.book.title
        // Load book image from URL
        Picasso.get().load(cartItem.book.imageLink[0]).fit()
            .placeholder(R.mipmap.ic_launcher)
            .error(R.mipmap.ic_launcher)
            .into(bookImage)
    }
}
