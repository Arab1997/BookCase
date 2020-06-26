package com.shivamkumarjha.bookstore.ui.wishitems.adapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.shivamkumarjha.bookstore.R
import com.shivamkumarjha.bookstore.model.WishItem
import com.squareup.picasso.Picasso

class WishItemsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val bookImage: ImageView = itemView.findViewById(R.id.wish_image_view_id)
    private val bookTitle: TextView = itemView.findViewById(R.id.wish_text_view_title)
    private val bookAuthor: TextView = itemView.findViewById(R.id.wish_text_view_author)

    fun initialize(review: WishItem) {
        bookTitle.text = review.book.title
        bookAuthor.text = review.book.author

        // Load book image from URL
        Picasso.get().load(review.book.imageLink[0]).fit().centerCrop()
            .placeholder(R.mipmap.ic_launcher)
            .error(R.mipmap.ic_launcher)
            .into(bookImage)
    }
}