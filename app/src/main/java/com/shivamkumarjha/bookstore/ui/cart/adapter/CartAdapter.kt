package com.shivamkumarjha.bookstore.ui.cart.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.shivamkumarjha.bookstore.R
import com.shivamkumarjha.bookstore.model.CartItem

class CartAdapter(private val clickListener: CartItemClickListener) :
    RecyclerView.Adapter<CartViewHolder>() {

    private var cartItems: ArrayList<CartItem> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val itemView =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.content_cart_item, parent, false)
        return CartViewHolder(itemView, clickListener)
    }

    override fun getItemCount(): Int = cartItems.size

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        holder.initialize(cartItems[position], position)
    }

    fun setCarts(cartItems: ArrayList<CartItem>) {
        this.cartItems = cartItems
        notifyDataSetChanged()
    }

    fun getCarts(): ArrayList<CartItem> = cartItems
}
