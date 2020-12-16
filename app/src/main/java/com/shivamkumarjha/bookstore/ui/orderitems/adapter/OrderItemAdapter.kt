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

class OrderItemAdapter(private val clickListener: OrderClickListener) :
    RecyclerView.Adapter<OrderItemAdapter.ViewHolder>() {
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
        val order = orders[position]
        holder.orderId.text = "Номер заказа: ${order.orderId}"
        holder.orderTime.text = order.timestamp
        //price
        var price = 0f
        for (index in 0 until order.cartItem.size) {
            price += order.cartItem[index].book.price
        }
        holder.orderPrice.text = "сум " + price * 76.25f // Price USD to INR
        // order-book recycler view
        val bookItemLayout =
            LinearLayoutManager(holder.bookRecyclerView.context, RecyclerView.HORIZONTAL, false)
        holder.bookRecyclerView.apply {
            layoutManager = bookItemLayout
            adapter = BookItemAdapter(order.cartItem, clickListener)
            setRecycledViewPool(viewPool)
        }
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val bookRecyclerView: RecyclerView = itemView.findViewById(R.id.recyclerview_order_books)
        val orderId: TextView = itemView.findViewById(R.id.order_id_text_view)
        val orderTime: TextView = itemView.findViewById(R.id.order_time_text_view)
        val orderPrice: TextView = itemView.findViewById(R.id.order_price_text_view)
    }
}