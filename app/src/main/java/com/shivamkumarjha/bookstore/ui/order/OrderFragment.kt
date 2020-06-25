package com.shivamkumarjha.bookstore.ui.order

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import com.shivamkumarjha.bookstore.R
import com.shivamkumarjha.bookstore.model.Order
import com.shivamkumarjha.bookstore.repository.CartRepository
import com.shivamkumarjha.bookstore.repository.OrderRepository
import java.io.File

class OrderFragment(private val order: Order) : Fragment() {

    private lateinit var orderIdTextView: TextView
    private lateinit var shoppingButton: Button
    private lateinit var orderRepository: OrderRepository
    private lateinit var cartRepository: CartRepository

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_order, container, false)
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        backPressDispatcher()

        // order JSON
        val orderFile = File(requireActivity().filesDir, resources.getString(R.string.file_order))
        orderRepository = OrderRepository(orderFile)
        orderRepository.addOrder(order)

        // empty cart JSON
        val cartFile = File(requireActivity().filesDir, resources.getString(R.string.file_cart))
        cartRepository = CartRepository(cartFile)
        cartRepository.makeCartEmpty()

        //view
        orderIdTextView = requireView().findViewById(R.id.order_id_view_id)
        orderIdTextView.text = "${order.orderId} ${order.timestamp}"
        shoppingButton = requireView().findViewById(R.id.order_shopping_button)
        shoppingButton.setOnClickListener {
            exitFragment()
        }
    }

    private fun backPressDispatcher() {
        val callBackObject = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                exitFragment()
            }
        }
        activity?.onBackPressedDispatcher?.addCallback(viewLifecycleOwner, callBackObject)
    }

    private fun exitFragment() {
        requireActivity().supportFragmentManager.popBackStack()
        requireActivity().finish()
    }
}