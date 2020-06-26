package com.shivamkumarjha.bookstore.ui.cart

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.shivamkumarjha.bookstore.R
import com.shivamkumarjha.bookstore.common.AppPreference
import com.shivamkumarjha.bookstore.model.CartItem
import com.shivamkumarjha.bookstore.repository.UserRepository
import com.shivamkumarjha.bookstore.ui.DashboardActivity
import com.shivamkumarjha.bookstore.ui.cart.adapter.CartAdapter
import com.shivamkumarjha.bookstore.ui.cart.adapter.CartItemClickListener
import java.io.File

class CartFragment : Fragment(), CartItemClickListener {

    private lateinit var toolbar: Toolbar
    private lateinit var cartAdapter: CartAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var userRepository: UserRepository
    private lateinit var cartViewModel: CartViewModel
    private lateinit var buyButton: Button

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_cart, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializer()
        setUpViews()
        setUpToolBar()
        setUpViewModel()
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
        toolbar = requireView().findViewById(R.id.cart_toolbar_id)
        toolbar.setNavigationIcon(R.drawable.ic_back)
        toolbar.setNavigationOnClickListener { exitFragment() }
        toolbar.title = resources.getString(R.string.title_cart)
    }

    private fun initializer() {
        buyButton = requireView().findViewById(R.id.cart_proceed_button)
        recyclerView = requireView().findViewById(R.id.cart_recycler_view_id)
        val userFile = File(requireActivity().filesDir, resources.getString(R.string.file_users))
        userRepository = UserRepository(userFile, AppPreference(requireContext()).getUserEmail()!!)
        cartViewModel = ViewModelProvider(this, CartViewModelFactory(userRepository))
            .get(CartViewModel::class.java)
    }

    private fun setUpViews() {
        // recycler view
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.setHasFixedSize(true)
        cartAdapter = CartAdapter(this)
        recyclerView.adapter = cartAdapter

        buyButton.setOnClickListener {
            exitFragment()
            (activity as DashboardActivity).callPurchaseActivity()
        }
    }

    private fun setUpViewModel() {
        cartViewModel.getCart().observe(viewLifecycleOwner, Observer {
            if (it.size > 0)
                buyButton.isEnabled = true
            else {
                buyButton.isEnabled = false
                buyButton.text = resources.getString(R.string.cart_empty)
            }
            cartAdapter.setCarts(it)
        })
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

    override fun onAddQuantity(cartItem: CartItem) {
        cartViewModel.updateCart(cartItem)
    }

    override fun onMinusQuantity(cartItem: CartItem, position: Int) {
        if (cartItem.quantity <= 0) {
            cartViewModel.removeCart(cartItem)
            val list: ArrayList<CartItem> = cartAdapter.getCarts()
            list.removeAt(position)
            cartAdapter.setCarts(list)
            cartAdapter.notifyItemRemoved(position)
            return
        }
        cartViewModel.updateCart(cartItem)
    }
}