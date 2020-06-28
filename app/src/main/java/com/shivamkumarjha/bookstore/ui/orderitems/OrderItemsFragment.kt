package com.shivamkumarjha.bookstore.ui.orderitems

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.shivamkumarjha.bookstore.R
import com.shivamkumarjha.bookstore.common.AppPreference
import com.shivamkumarjha.bookstore.model.Book
import com.shivamkumarjha.bookstore.repository.UserRepository
import com.shivamkumarjha.bookstore.ui.DashboardActivity
import com.shivamkumarjha.bookstore.ui.orderitems.adapter.OrderClickListener
import com.shivamkumarjha.bookstore.ui.orderitems.adapter.OrderItemAdapter
import java.io.File

class OrderItemsFragment : Fragment(), OrderClickListener {

    private lateinit var toolbar: Toolbar
    private lateinit var orderItemAdapter: OrderItemAdapter
    private lateinit var recyclerView: RecyclerView

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
        setUpRecyclerView()
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

    private fun setUpRecyclerView() {
        val userFile = File(requireActivity().filesDir, resources.getString(R.string.file_users))
        val userRepository =
            UserRepository(userFile, AppPreference(requireContext()).getUserEmail()!!)

        recyclerView = requireView().findViewById(R.id.order_list_list_recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.setHasFixedSize(true)
        orderItemAdapter = OrderItemAdapter(this)
        orderItemAdapter.setOrders(userRepository.getOrders())
        recyclerView.adapter = orderItemAdapter
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

    override fun onBookClick(book: Book) {
        (activity as DashboardActivity).callDisplayBookFragment(book)
    }

    override fun onBookReviewClick(book: Book) {
        (activity as DashboardActivity).callReviewFragment(book)
    }
}