package com.shivamkumarjha.bookstore.ui.displaybook.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.shivamkumarjha.bookstore.R
import com.shivamkumarjha.bookstore.model.Review

class ReviewAdapter : RecyclerView.Adapter<ReviewViewHolder>() {

    private var reviews: ArrayList<Review> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReviewViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.content_review_item, parent, false)
        return ReviewViewHolder(itemView)
    }

    override fun getItemCount(): Int = reviews.size

    override fun onBindViewHolder(holder: ReviewViewHolder, position: Int) {
        holder.initialize(reviews[position])
    }

    fun setReviews(reviews: ArrayList<Review>) {
        this.reviews = reviews
    }
}