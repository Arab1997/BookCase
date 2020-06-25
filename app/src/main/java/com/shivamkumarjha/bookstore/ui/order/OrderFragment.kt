package com.shivamkumarjha.bookstore.ui.order

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.shivamkumarjha.bookstore.R
import com.shivamkumarjha.bookstore.model.Order
import com.shivamkumarjha.bookstore.repository.UserRepository
import java.io.File

class OrderFragment(private val order: Order) : Fragment() {

    private lateinit var orderIdTextView: TextView
    private lateinit var shoppingButton: Button
    private lateinit var userRepository: UserRepository

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
        (activity as AppCompatActivity).supportActionBar?.title =
            resources.getString(R.string.order)

        // order JSON
        val userFile = File(requireActivity().filesDir, resources.getString(R.string.file_users))
        userRepository = UserRepository(userFile)
        userRepository.addOrder(order)
        userRepository.makeCartEmpty()

        //view
        orderIdTextView = requireView().findViewById(R.id.order_id_view_id)
        orderIdTextView.text = "ID: ${order.orderId} ${order.timestamp}"
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