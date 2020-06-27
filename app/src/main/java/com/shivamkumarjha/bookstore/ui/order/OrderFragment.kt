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
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import com.shivamkumarjha.bookstore.R
import com.shivamkumarjha.bookstore.common.AppPreference
import com.shivamkumarjha.bookstore.model.Order
import com.shivamkumarjha.bookstore.repository.UserRepository
import com.shivamkumarjha.bookstore.ui.DashboardActivity
import java.io.File

class OrderFragment(private val order: Order) : Fragment() {

    private lateinit var toolbar: Toolbar
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
        setUpToolBar()

        // order JSON
        val userFile = File(requireActivity().filesDir, resources.getString(R.string.file_users))
        userRepository = UserRepository(userFile, AppPreference(requireContext()).getUserEmail()!!)
        userRepository.addOrder(order)
        userRepository.makeCartItemsEmpty()

        //view
        orderIdTextView = requireView().findViewById(R.id.order_id_view_id)
        orderIdTextView.text = "ID: ${order.orderId} ${order.timestamp}"
        shoppingButton = requireView().findViewById(R.id.order_shopping_button)
        shoppingButton.setOnClickListener {
            exitFragment()
        }
    }

    override fun onResume() {
        super.onResume()
        (activity as AppCompatActivity).supportActionBar!!.hide()
    }

    override fun onStop() {
        super.onStop()
        (activity as AppCompatActivity).supportActionBar!!.show()
    }

    private fun setUpToolBar() {
        toolbar = requireView().findViewById(R.id.order_toolbar_id)
        toolbar.setNavigationIcon(R.drawable.ic_back)
        toolbar.setNavigationOnClickListener { exitFragment() }
        toolbar.title = resources.getString(R.string.order)
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
        (activity as DashboardActivity).callBookFragment()
    }
}