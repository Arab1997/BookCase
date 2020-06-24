package com.shivamkumarjha.bookstore.ui.delivery

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
import com.shivamkumarjha.bookstore.repository.AddressRepository
import com.shivamkumarjha.bookstore.repository.CartRepository
import com.shivamkumarjha.bookstore.ui.PurchaseActivity
import com.shivamkumarjha.bookstore.ui.delivery.adapter.DeliveryAdapter
import com.shivamkumarjha.bookstore.ui.delivery.adapter.DeliveryItemClickListener
import java.io.File

class DeliveryFragment : Fragment(), DeliveryItemClickListener {

    private lateinit var deliveryAdapter: DeliveryAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var deliveryViewModel: DeliveryViewModel
    private lateinit var cartRepository: CartRepository
    private var addressList: ArrayList<Address> = arrayListOf()

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
        recyclerView = requireView().findViewById(R.id.delivery_recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.setHasFixedSize(true)
        val cartFile = File(requireActivity().filesDir, resources.getString(R.string.file_cart))
        cartRepository = CartRepository(cartFile)
        val addressFile =
            File(requireActivity().filesDir, resources.getString(R.string.file_address))
        val addressRepository = AddressRepository(addressFile)
        deliveryViewModel =
            ViewModelProvider(this, DeliveryViewModelFactory(addressRepository))
                .get(DeliveryViewModel::class.java)
        deliveryViewModel.getAddress().observe(viewLifecycleOwner, Observer {
            addressList = it
            if (it.size == 0)
                Snackbar.make(
                    view,
                    "Please add address & retry.", Snackbar.LENGTH_LONG
                ).setAction(R.string.add_address) {
                    (activity as PurchaseActivity).callAddressFragment(null)
                }.show()
            deliveryAdapter = DeliveryAdapter(addressList, this)
            recyclerView.adapter = deliveryAdapter
        })
    }

    override fun onAddressClick(address: Address) {
        (activity as PurchaseActivity).callOrderFragment(
            Order(
                AppPreference(requireContext()).newOrderId(),
                cartRepository.getCart(),
                address
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