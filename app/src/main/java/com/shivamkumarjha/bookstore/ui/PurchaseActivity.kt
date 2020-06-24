package com.shivamkumarjha.bookstore.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.FragmentTransaction
import com.shivamkumarjha.bookstore.R
import com.shivamkumarjha.bookstore.model.Address
import com.shivamkumarjha.bookstore.ui.delivery.DeliveryFragment
import com.shivamkumarjha.bookstore.ui.order.OrderFragment

class PurchaseActivity : AppCompatActivity() {

    private lateinit var toolbar: Toolbar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_purchase)
        setUpToolbar()
        callDeliveryFragment()
    }

    private fun setUpToolbar() {
        toolbar = findViewById(R.id.purchase_toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.title = resources.getString(R.string.chose_delivery)
    }

    private fun callDeliveryFragment() {
        supportFragmentManager.beginTransaction()
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            .replace(R.id.purchase_fragment_holder, DeliveryFragment())
            .commit()
    }

    fun callOrderFragment(address: Address) {
        supportFragmentManager.beginTransaction()
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            .replace(R.id.purchase_fragment_holder, OrderFragment(address))
            .commit()
    }
}