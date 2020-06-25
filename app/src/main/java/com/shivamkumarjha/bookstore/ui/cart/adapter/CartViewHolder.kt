package com.shivamkumarjha.bookstore.ui.cart.adapter

import android.annotation.SuppressLint
import android.graphics.Paint
import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.shivamkumarjha.bookstore.R
import com.shivamkumarjha.bookstore.model.Cart
import com.squareup.picasso.Picasso

class CartViewHolder(
    itemView: View,
    private val clickListener: CartItemClickListener
) : RecyclerView.ViewHolder(itemView) {
    private val bookImage: ImageView = itemView.findViewById(R.id.cart_image_view_id)
    private val bookTitle: TextView = itemView.findViewById(R.id.cart_text_view_title)
    private val bookAuthor: TextView = itemView.findViewById(R.id.cart_text_view_author)
    private val bookPrice: TextView = itemView.findViewById(R.id.cart_text_view_price)
    private val bookMRP: TextView = itemView.findViewById(R.id.cart_text_view_mrp)
    private val bookSaved: TextView = itemView.findViewById(R.id.cart_text_view_saved)
    private val bookQuantity: TextView = itemView.findViewById(R.id.display_book_cart_value_id)
    private val minusButton: ImageButton = itemView.findViewById(R.id.display_book_cart_minus_id)
    private val addButton: ImageButton = itemView.findViewById(R.id.display_book_cart_add_id)
    private var cartPosition: Int = 0
    private lateinit var cart: Cart

    init {
        addButton.setOnClickListener {
            cart.quantity++
            setPrices()
            clickListener.onAddQuantity(cart)
        }
        minusButton.setOnClickListener {
            cart.quantity--
            setPrices()
            clickListener.onMinusQuantity(cart, cartPosition)
        }
    }

    @SuppressLint("SetTextI18n")
    fun initialize(cart: Cart, cartPosition: Int) {
        this.cartPosition = cartPosition
        this.cart = cart
        bookTitle.text = cart.book?.title
        bookAuthor.text = "By " + cart.book?.author
        setPrices()

        // Load book image from URL
        if (cart.book != null)
            Picasso.get().load(cart.book.imageLink[0]).fit().centerCrop()
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)
                .into(bookImage)
    }

    @SuppressLint("SetTextI18n")
    private fun setPrices() {
        val bookTotalPrice = cart.book?.price?.times(76.25f * cart.quantity)
        val bookTotalMRP = cart.book?.maximumRetailPrice?.times(76.25f * cart.quantity)
        bookPrice.text = "Rs $bookTotalPrice"
        bookQuantity.text = cart.quantity.toString()
        bookMRP.text = "Rs $bookTotalMRP"
        bookMRP.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
        if (cart.book != null)
            bookSaved.text = "Rs " + getAmountSaved(bookTotalPrice!!, bookTotalMRP!!) + " saved"
    }

    private fun getAmountSaved(price: Float, maximumRetailPrice: Float): Float {
        return maximumRetailPrice - price
    }
}
