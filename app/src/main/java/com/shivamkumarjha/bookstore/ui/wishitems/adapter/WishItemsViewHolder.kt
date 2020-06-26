package com.shivamkumarjha.bookstore.ui.wishitems.adapter

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.shivamkumarjha.bookstore.R
import com.shivamkumarjha.bookstore.model.WishItem

class WishItemsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val reviewAuthor: TextView = itemView.findViewById(R.id.review_author_text_view_id)
    private val reviewDetail: TextView = itemView.findViewById(R.id.review_detail_text_view_id)

    fun initialize(review: WishItem) {
        reviewAuthor.text = review.book.title
        reviewDetail.text = review.book.author
    }
}