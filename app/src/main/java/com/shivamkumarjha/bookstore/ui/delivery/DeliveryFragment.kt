package com.shivamkumarjha.bookstore.ui.delivery

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.shivamkumarjha.bookstore.R
import com.shivamkumarjha.bookstore.common.AppPreference
import com.shivamkumarjha.bookstore.model.Address
import com.shivamkumarjha.bookstore.model.Order
import com.shivamkumarjha.bookstore.repository.UserRepository
import com.shivamkumarjha.bookstore.ui.PurchaseActivity
import com.shivamkumarjha.bookstore.ui.delivery.adapter.DeliveryAdapter
import com.shivamkumarjha.bookstore.ui.delivery.adapter.DeliveryItemClickListener
import java.io.File
import java.text.SimpleDateFormat
import java.util.*

class DeliveryFragment : Fragment(), DeliveryItemClickListener {

    private lateinit var deliveryAdapter: DeliveryAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var deliveryViewModel: DeliveryViewModel
    private lateinit var userRepository: UserRepository

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_delivery, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        backPressDispatcher()

        //recycler view
        recyclerView = requireView().findViewById(R.id.delivery_recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.setHasFixedSize(true)
        deliveryAdapter = DeliveryAdapter(this)
        recyclerView.adapter = deliveryAdapter

        //repository
        val userFile = File(requireActivity().filesDir, resources.getString(R.string.file_users))
        userRepository = UserRepository(userFile, AppPreference(requireContext()).getUserEmail()!!)

        //view model
        deliveryViewModel =
            ViewModelProvider(this, DeliveryViewModelFactory(userRepository))
                .get(DeliveryViewModel::class.java)
        deliveryViewModel.getAddress().observe(viewLifecycleOwner, Observer {
            if (it.size == 0)
                Snackbar.make(
                    view,
                    resources.getString(R.string.no_address), Snackbar.LENGTH_INDEFINITE
                ).setAction(R.string.add_address) {
                    (activity as PurchaseActivity).callAddressFragment(null)
                }.show()
            deliveryAdapter.setAddress(it)
        })
    }

    @SuppressLint("SimpleDateFormat")
    override fun onAddressClick(address: Address) {
        val sdf = SimpleDateFormat("dd/MM/yyyy hh:mm:ss")
        val currentDate = sdf.format(Date())
        (activity as PurchaseActivity).callOrderFragment(
            Order(
                AppPreference(requireContext()).newOrderId(),
                userRepository.getCart(),
                address,
                currentDate
            )
        )
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