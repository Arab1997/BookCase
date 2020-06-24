package com.shivamkumarjha.bookstore.ui.cart

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.shivamkumarjha.bookstore.R
import com.shivamkumarjha.bookstore.model.Cart
import com.shivamkumarjha.bookstore.repository.CartRepository
import com.shivamkumarjha.bookstore.ui.cart.adapter.CartAdapter
import com.shivamkumarjha.bookstore.ui.cart.adapter.CartItemClickListener
import java.io.File

class CartFragment : Fragment(), CartItemClickListener {

    private lateinit var toolbar: Toolbar
    private lateinit var cartAdapter: CartAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var cartRepository: CartRepository
    private lateinit var cartViewModel: CartViewModel

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
        recyclerView = requireView().findViewById(R.id.cart_recycler_view_id)
        val cartFile = File(requireActivity().filesDir, resources.getString(R.string.file_cart))
        cartRepository = CartRepository(cartFile)
        cartViewModel = ViewModelProvider(this, CartViewModelFactory(cartRepository))
            .get(CartViewModel::class.java)
    }

    private fun setUpViews() {
        // recycler view
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.setHasFixedSize(true)
    }

    private fun setUpViewModel() {
        cartViewModel.getCart().observe(viewLifecycleOwner, Observer {
            cartAdapter = CartAdapter(it, this)
            recyclerView.adapter = cartAdapter
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

    override fun onAddQuantity(cart: Cart) {
        TODO("Not yet implemented")
    }

    override fun onMinusQuantity(cart: Cart, position: Int) {
        TODO("Not yet implemented")
    }
}