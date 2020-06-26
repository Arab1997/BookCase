package com.shivamkumarjha.bookstore.ui.wishitems.adapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.shivamkumarjha.bookstore.R
import com.shivamkumarjha.bookstore.model.Book
import com.shivamkumarjha.bookstore.model.WishItem
import com.squareup.picasso.Picasso

class WishItemsViewHolder(itemView: View, private val bookClickListener: BookClickListener) :
    RecyclerView.ViewHolder(itemView) {
    private val bookImage: ImageView = itemView.findViewById(R.id.wish_image_view_id)
    private val bookTitle: TextView = itemView.findViewById(R.id.wish_text_view_title)
    private val bookAuthor: TextView = itemView.findViewById(R.id.wish_text_view_author)
    private val cardView: CardView = itemView.findViewById(R.id.wish_card_view_id)
    private lateinit var book: Book

    init {
        cardView.setOnClickListener {
            bookClickListener.onBookClick(book)
        }
    }

    fun initialize(review: WishItem) {
        this.book = review.book
        bookTitle.text = review.book.title
        bookAuthor.text = review.book.author

        // Load book image from URL
        Picasso.get().load(review.book.imageLink[0]).fit().centerCrop()
            .placeholder(R.mipmap.ic_launcher)
            .error(R.mipmap.ic_launcher)
            .into(bookImage)
    }
}