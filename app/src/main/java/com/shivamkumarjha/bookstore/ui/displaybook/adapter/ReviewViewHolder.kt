package com.shivamkumarjha.bookstore.ui.displaybook.adapter

import android.view.View
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.shivamkumarjha.bookstore.R
import com.shivamkumarjha.bookstore.model.Review

class ReviewViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val reviewAuthor: TextView = itemView.findViewById(R.id.review_author_text_view_id)
    private val reviewDetail: TextView = itemView.findViewById(R.id.review_detail_text_view_id)
    private val reviewRating: RatingBar = itemView.findViewById(R.id.review_item_rating_bar_id)

    fun initialize(review: Review) {
        reviewAuthor.text = review.author
        reviewDetail.text = review.review
        reviewRating.rating = review.rating
    }
}