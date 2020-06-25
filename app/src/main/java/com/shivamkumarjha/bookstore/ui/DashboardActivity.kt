package com.shivamkumarjha.bookstore.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.FragmentTransaction
import com.shivamkumarjha.bookstore.R
import com.shivamkumarjha.bookstore.common.AppPreference
import com.shivamkumarjha.bookstore.model.Address
import com.shivamkumarjha.bookstore.model.Book
import com.shivamkumarjha.bookstore.model.LoggedInUserView
import com.shivamkumarjha.bookstore.repository.AddressRepository
import com.shivamkumarjha.bookstore.repository.CartRepository
import com.shivamkumarjha.bookstore.repository.OrderRepository
import com.shivamkumarjha.bookstore.ui.address.AddressFragment
import com.shivamkumarjha.bookstore.ui.booklist.BookListFragment
import com.shivamkumarjha.bookstore.ui.cart.CartFragment
import com.shivamkumarjha.bookstore.ui.displaybook.DisplayBookFragment
import com.shivamkumarjha.bookstore.ui.login.LoginActivity
import com.shivamkumarjha.bookstore.ui.profile.ProfileFragment
import java.io.File

class DashboardActivity : AppCompatActivity() {

    private lateinit var toolbar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)
        setUpToolbar()
        getUser()
        callBookFragment()
    }

    private fun setUpToolbar() {
        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.title = resources.getString(R.string.app_name)
    }

    fun getUser(): LoggedInUserView {
        val bundle = intent.getBundleExtra("bundle")
        val loggedInUserView = bundle?.getParcelable<LoggedInUserView>("loggedInUserView")

        if (loggedInUserView != null) {
            AppPreference(this).setUserEmail(loggedInUserView.email)
            AppPreference(this).setUserName(loggedInUserView.name)
            return loggedInUserView
        }
        return LoggedInUserView(
            AppPreference(this).getUserName()!!,
            AppPreference(this).getUserEmail()!!
        )
    }

    private fun callBookFragment() {
        supportFragmentManager.beginTransaction()
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            .replace(R.id.fragment_holder, BookListFragment())
            .commit()
    }

    fun callDisplayBookFragment(book: Book) {
        supportFragmentManager.beginTransaction()
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            .replace(R.id.fragment_holder, DisplayBookFragment(book))
            .addToBackStack(null)
            .commit()
    }

    fun callCartFragment() {
        supportFragmentManager.beginTransaction()
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            .replace(R.id.fragment_holder, CartFragment())
            .addToBackStack(null)
            .commit()
    }

    fun callProfileFragment() {
        supportFragmentManager.beginTransaction()
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            .replace(R.id.fragment_holder, ProfileFragment())
            .addToBackStack(null)
            .commit()
    }

    fun callAddressFragment(address: Address?) {
        supportFragmentManager.beginTransaction()
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            .replace(R.id.fragment_holder, AddressFragment(address))
            .addToBackStack(null)
            .commit()
    }

    fun callPurchaseActivity() {
        val intent = Intent(this, PurchaseActivity::class.java)
        startActivity(intent)
    }

    fun doSignOut() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle(resources.getString(R.string.action_sign_out))
        builder.setMessage(resources.getString(R.string.action_sign_out_verify))
        builder.setPositiveButton(R.string.yes) { _, _ ->
            // clear user data
            AppPreference(this).setSignIn(false)
            val addressFile = File(filesDir, resources.getString(R.string.file_address))
            val addressRepository = AddressRepository(addressFile)
            addressRepository.removeAll()
            val cartFile = File(filesDir, resources.getString(R.string.file_cart))
            val cartRepository = CartRepository(cartFile)
            cartRepository.makeCartEmpty()
            val orderFile = File(filesDir, resources.getString(R.string.file_order))
            val orderRepository = OrderRepository(orderFile)
            orderRepository.removeAll()

            //start login
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            this.finish()
        }
        builder.setNegativeButton(R.string.no) { _, _ -> }
        builder.show()
    }
}