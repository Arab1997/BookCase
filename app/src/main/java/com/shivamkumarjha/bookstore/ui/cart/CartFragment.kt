package com.shivamkumarjha.bookstore.ui.cart

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.shivamkumarjha.bookstore.R
import com.shivamkumarjha.bookstore.ui.book.BookFragment

class CartFragment : Fragment() {

    private lateinit var cartViewModel: CartViewModel
    private val textView by lazy {
        requireView().findViewById<TextView>(R.id.text_cart)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_cart, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpViewModel()
        backPressDispatcher()
    }

    private fun setUpViewModel() {
        cartViewModel =
            ViewModelProvider(this).get(CartViewModel::class.java)
        cartViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
    }

    private fun backPressDispatcher() {
        activity?.onBackPressedDispatcher?.addCallback(
            viewLifecycleOwner,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    callBookFragment()
                }
            })
    }

    private fun callBookFragment() {
        requireActivity().supportFragmentManager.popBackStack()
        requireActivity().supportFragmentManager.beginTransaction()
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            .replace(R.id.fragment_holder, BookFragment())
            .commit()
    }
}