package com.shivamkumarjha.bookstore.ui.booklist.adapter

import android.annotation.SuppressLint
import android.graphics.Paint
import android.view.View
import android.view.animation.Animation
import android.view.animation.BounceInterpolator
import android.view.animation.ScaleAnimation
import android.widget.CompoundButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.ToggleButton
import androidx.recyclerview.widget.RecyclerView
import com.shivamkumarjha.bookstore.R
import com.shivamkumarjha.bookstore.model.Book
import com.shivamkumarjha.bookstore.model.WishItem
import com.squareup.picasso.Picasso

class BookViewHolder(itemView: View, private val clickListener: BookItemClickListener) :
    RecyclerView.ViewHolder(itemView) {
    private val bookImage: ImageView = itemView.findViewById(R.id.book_image_view_id)
    private val bookTitle: TextView = itemView.findViewById(R.id.book_text_view_title)
    private val bookAuthor: TextView = itemView.findViewById(R.id.book_text_view_author)
    private val bookPrice: TextView = itemView.findViewById(R.id.book_text_view_price)
    private val bookMRP: TextView = itemView.findViewById(R.id.book_text_view_mrp)
    private val bookDiscount: TextView = itemView.findViewById(R.id.book_text_view_discount)
    private val bookRating: TextView = itemView.findViewById(R.id.book_text_view_rating)
    private val bookRatingCount: TextView = itemView.findViewById(R.id.book_text_view_rating_count)
    private val wishStatus: ToggleButton = itemView.findViewById(R.id.book_toggle_wish_id)
    private lateinit var book: Book

    init {
        itemView.setOnClickListener {
            clickListener.onBookClick(book)
        }
    }

    @SuppressLint("SetTextI18n")
    fun initialize(book: Book, wishItems: ArrayList<WishItem>) {
        this.book = book

        // Load book image from URL
        Picasso.get().load(book.imageLink[0]).fit().centerCrop()
            .placeholder(R.mipmap.ic_launcher)
            .error(R.mipmap.ic_launcher)
            .into(bookImage)

        bookTitle.text = book.title
        bookAuthor.text = "Автор " + book.author
        bookPrice.text = "сум " + book.price * 762.5 // Price USD to INR
        bookMRP.text = " " + book.maximumRetailPrice * 762.5 // Price USD to INR
        bookMRP.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
        bookDiscount.text = book.discount.toInt().toString() + "% скидка"
        bookRating.text = "%.1f".format(book.review.map { it.rating }.average()) // Average rating
        bookRatingCount.text = "${book.review.size} отзывы"

        // wish toggle
        wishStatus.isChecked = isBookInWishItems(book, wishItems)
        wishStatus.setOnClickListener {
            clickListener.onWishClick(book, wishStatus.isChecked)
        }
        val scaleAnimation = ScaleAnimation(
            0.7f,
            1.0f,
            0.7f,
            1.0f,
            Animation.RELATIVE_TO_SELF,
            0.7f,
            Animation.RELATIVE_TO_SELF,
            0.7f
        )
        scaleAnimation.duration = 500
        scaleAnimation.interpolator = BounceInterpolator()
        wishStatus.setOnCheckedChangeListener(object : View.OnClickListener,
            CompoundButton.OnCheckedChangeListener {
            override fun onCheckedChanged(compoundButton: CompoundButton?, status: Boolean) {
                compoundButton?.startAnimation(scaleAnimation)
            }

            override fun onClick(p0: View?) {}
        })
    }

    private fun isBookInWishItems(book: Book, wishItems: ArrayList<WishItem>): Boolean {
        wishItems.forEach {
            if (it.book.bookID == book.bookID)
                return true
        }
        return false
    }
}