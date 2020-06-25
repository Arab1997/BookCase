package com.shivamkumarjha.bookstore.ui.cart.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.shivamkumarjha.bookstore.R
import com.shivamkumarjha.bookstore.model.Cart

class CartAdapter(private val clickListener: CartItemClickListener) :
    RecyclerView.Adapter<CartViewHolder>() {

    private var carts: ArrayList<Cart> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val itemView =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.content_cart_item, parent, false)
        return CartViewHolder(itemView, clickListener)
    }

    override fun getItemCount(): Int = carts.size

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        holder.initialize(carts[position], position)
    }

    fun setCarts(carts: ArrayList<Cart>) {
        this.carts = carts
        notifyDataSetChanged()
    }

    fun getCarts(): ArrayList<Cart> = carts
}
