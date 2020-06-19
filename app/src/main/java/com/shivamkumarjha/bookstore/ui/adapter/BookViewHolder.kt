package com.shivamkumarjha.bookstore.ui.adapter

import android.annotation.SuppressLint
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.ToggleButton
import androidx.recyclerview.widget.RecyclerView
import com.shivamkumarjha.bookstore.R
import com.shivamkumarjha.bookstore.model.Book
import com.squareup.picasso.Picasso

class BookViewHolder(itemView: View, clickListener: BookItemClickListener) :
    RecyclerView.ViewHolder(itemView) {
    private val bookImage: ImageView = itemView.findViewById(R.id.book_image_view_id)
    private val bookTitle: TextView = itemView.findViewById(R.id.book_text_view_title)
    private val bookAuthor: TextView = itemView.findViewById(R.id.book_text_view_author)
    private val bookPrice: TextView = itemView.findViewById(R.id.book_text_view_price)
    private val bookRating: TextView = itemView.findViewById(R.id.book_text_view_rating)
    private val wishStatus: ToggleButton = itemView.findViewById(R.id.book_toggle_wish_id)
    private lateinit var book: Book

    init {
        itemView.setOnClickListener {
            clickListener.onBookClick(book)
        }
    }

    @SuppressLint("SetTextI18n")
    fun initialize(book: Book) {
        this.book = book
        Picasso.get().load(book.imageLink).into(bookImage)
        bookTitle.text = book.title
        bookAuthor.text = book.author
        bookPrice.text = "$" + book.price
        bookRating.text = book.rating
        wishStatus.isChecked = book.inWishList
    }
}