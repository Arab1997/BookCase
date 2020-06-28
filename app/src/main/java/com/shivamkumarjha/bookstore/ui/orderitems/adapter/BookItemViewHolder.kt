package com.shivamkumarjha.bookstore.ui.orderitems.adapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.shivamkumarjha.bookstore.R
import com.shivamkumarjha.bookstore.model.Book
import com.shivamkumarjha.bookstore.model.CartItem
import com.shivamkumarjha.bookstore.ui.wishitems.adapter.BookClickListener
import com.squareup.picasso.Picasso

class BookItemViewHolder(itemView: View, private val clickListener: BookClickListener) :
    RecyclerView.ViewHolder(itemView) {
    private val bookTitle: TextView = itemView.findViewById(R.id.order_book_title_text_view_id)
    private val bookImage: ImageView = itemView.findViewById(R.id.order_book_image_text_view_id)
    private val bookCard: CardView = itemView.findViewById(R.id.card_order_book_view_id)
    private lateinit var book: Book

    init {
        bookCard.setOnClickListener {
            clickListener.onBookClick(book)
        }
    }

    fun initialize(cartItem: CartItem) {
        this.book = cartItem.book
        bookTitle.text = cartItem.book.title
        // Load book image from URL
        Picasso.get().load(cartItem.book.imageLink[0]).fit()
            .placeholder(R.mipmap.ic_launcher)
            .error(R.mipmap.ic_launcher)
            .into(bookImage)
    }
}
