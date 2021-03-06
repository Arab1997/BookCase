package com.shivamkumarjha.bookstore.ui.cartitems.adapter

import android.annotation.SuppressLint
import android.graphics.Paint
import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.shivamkumarjha.bookstore.R
import com.shivamkumarjha.bookstore.model.CartItem
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
    private lateinit var cartItem: CartItem

    init {
        addButton.setOnClickListener {
            cartItem.quantity++
            setPrices()
            clickListener.onAddQuantity(cartItem)
        }
        minusButton.setOnClickListener {
            cartItem.quantity--
            setPrices()
            clickListener.onMinusQuantity(cartItem, cartPosition)
        }
        bookImage.setOnClickListener {
            clickListener.onBookClick(cartItem.book)
        }
    }

    @SuppressLint("SetTextI18n")
    fun initialize(cartItem: CartItem, cartPosition: Int) {
        this.cartPosition = cartPosition
        this.cartItem = cartItem
        bookTitle.text = cartItem.book.title
        bookAuthor.text = "автор " + cartItem.book.author
        setPrices()

        // Load book image from URL
        Picasso.get().load(cartItem.book.imageLink[0]).fit().centerCrop()
            .placeholder(R.mipmap.ic_launcher)
            .error(R.mipmap.ic_launcher)
            .into(bookImage)
    }

    @SuppressLint("SetTextI18n")
    private fun setPrices() {
        val bookTotalPrice = cartItem.book.price * 76.25f * cartItem.quantity
        val bookTotalMRP = cartItem.book.maximumRetailPrice * 76.25f * cartItem.quantity
        bookPrice.text = "сум $bookTotalPrice"
        bookQuantity.text = cartItem.quantity.toString()
        bookMRP.text = "сум $bookTotalMRP"
        bookMRP.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
        bookSaved.text = "сум " + getAmountSaved(bookTotalPrice, bookTotalMRP) + " сохраненный"
    }

    private fun getAmountSaved(price: Float, maximumRetailPrice: Float): Float {
        return maximumRetailPrice - price
    }
}
