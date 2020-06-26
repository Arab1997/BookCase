package com.shivamkumarjha.bookstore.ui.orderitems

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.shivamkumarjha.bookstore.R
import com.shivamkumarjha.bookstore.model.Book
import com.shivamkumarjha.bookstore.ui.DashboardActivity

class OrderItemsFragment : Fragment() {

    private lateinit var toolbar: Toolbar

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_order_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpToolBar()
        backPressDispatcher()
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
        toolbar = requireView().findViewById(R.id.order_list_toolbar_id)
        toolbar.setNavigationIcon(R.drawable.ic_back)
        toolbar.setNavigationOnClickListener { exitFragment() }
        toolbar.title = resources.getString(R.string.orders)
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
    }
}