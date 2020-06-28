package com.shivamkumarjha.bookstore.ui.orderitems.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.shivamkumarjha.bookstore.R
import com.shivamkumarjha.bookstore.model.Order

class OrderItemAdapter : RecyclerView.Adapter<OrderItemAdapter.ViewHolder>() {
    private val viewPool = RecyclerView.RecycledViewPool()
    private var orders: ArrayList<Order> = arrayListOf()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.content_order_item, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return orders.size
    }

    fun setOrders(orders: ArrayList<Order>) {
        this.orders = orders
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int
    ) {
        val book = orders[position]
        holder.orderId.text = book.orderId.toString()
        val bookItemLayout = LinearLayoutManager(holder.bookRecyclerView.context, RecyclerView.HORIZONTAL, false)
        holder.bookRecyclerView.apply {
            layoutManager = bookItemLayout
            adapter = BookItemAdapter(book.cartItem)
            setRecycledViewPool(viewPool)
        }
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val bookRecyclerView: RecyclerView = itemView.findViewById(R.id.recyclerview_order_books)
        val orderId: TextView = itemView.findViewById(R.id.order_number_text_view)
    }
}