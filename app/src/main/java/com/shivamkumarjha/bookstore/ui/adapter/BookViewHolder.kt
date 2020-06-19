package com.shivamkumarjha.bookstore.ui.adapter

import android.annotation.SuppressLint
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.shivamkumarjha.bookstore.R
import com.shivamkumarjha.bookstore.model.Book

class BookViewHolder(itemView: View, clickListener: BookItemClickListener) :
    RecyclerView.ViewHolder(itemView) {
    private val bookImage: ImageView = itemView.findViewById(R.id.book_image_view_id)
    private val bookTitle: TextView = itemView.findViewById(R.id.book_text_view_title)
    private val bookAuthor: TextView = itemView.findViewById(R.id.book_text_view_author)
    private val bookPrice: TextView = itemView.findViewById(R.id.book_text_view_price)
    private val bookRating: TextView = itemView.findViewById(R.id.book_text_view_rating)
    private lateinit var book: Book

    init {
        itemView.setOnClickListener {
            clickListener.onBookClick(book)
        }
    }

    @SuppressLint("SetTextI18n")
    fun initialize(book: Book) {
        this.book = book
        // TODO use glide to get book cover from URL
        bookImage.setBackgroundResource(R.mipmap.ic_launcher)
        bookTitle.text = book.title
        bookAuthor.text = book.author
        bookPrice.text = "$" + book.price
        bookRating.text = book.rating
    }
}