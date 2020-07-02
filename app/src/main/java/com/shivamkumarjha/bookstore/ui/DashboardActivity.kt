package com.shivamkumarjha.bookstore.ui

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.shivamkumarjha.bookstore.R
import com.shivamkumarjha.bookstore.common.AppPreference
import com.shivamkumarjha.bookstore.model.Address
import com.shivamkumarjha.bookstore.model.Book
import com.shivamkumarjha.bookstore.model.LoggedInUserView
import com.shivamkumarjha.bookstore.model.Order
import com.shivamkumarjha.bookstore.ui.address.AddressFragment
import com.shivamkumarjha.bookstore.ui.booklist.BookListFragment
import com.shivamkumarjha.bookstore.ui.cartitems.CartFragment
import com.shivamkumarjha.bookstore.ui.delivery.DeliveryFragment
import com.shivamkumarjha.bookstore.ui.displaybook.DisplayBookFragment
import com.shivamkumarjha.bookstore.ui.login.LoginActivity
import com.shivamkumarjha.bookstore.ui.order.OrderFragment
import com.shivamkumarjha.bookstore.ui.orderitems.OrderItemsFragment
import com.shivamkumarjha.bookstore.ui.profile.ProfileFragment
import com.shivamkumarjha.bookstore.ui.review.ReviewFragment
import com.shivamkumarjha.bookstore.ui.wishitems.WishItemsFragment

class DashboardActivity : AppCompatActivity() {

    private lateinit var toolbar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)
        setUpToolbar()
        getUser()
        setupPermissions()
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

    fun callBookFragment() {
        supportFragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
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

    fun callOrderListFragment() {
        supportFragmentManager.beginTransaction()
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            .replace(R.id.fragment_holder, OrderItemsFragment())
            .addToBackStack(null)
            .commit()
    }

    fun callWishItemsFragment() {
        supportFragmentManager.beginTransaction()
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            .replace(R.id.fragment_holder, WishItemsFragment())
            .addToBackStack(null)
            .commit()
    }

    fun callDeliveryFragment() {
        supportFragmentManager.beginTransaction()
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            .replace(R.id.fragment_holder, DeliveryFragment())
            .addToBackStack(null)
            .commit()
    }

    fun callOrderFragment(order: Order) {
        supportFragmentManager.beginTransaction()
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            .replace(R.id.fragment_holder, OrderFragment(order))
            .addToBackStack(null)
            .commit()
    }

    fun callReviewFragment(book: Book) {
        supportFragmentManager.beginTransaction()
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            .replace(R.id.fragment_holder, ReviewFragment(book))
            .addToBackStack(null)
            .commit()
    }

    private fun setupPermissions() {
        val permissionsReadRequestCode = 101

        val readStoragePermission = ContextCompat.checkSelfPermission(
            this,
            Manifest.permission.READ_EXTERNAL_STORAGE
        )
        if (readStoragePermission != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
                permissionsReadRequestCode
            )
        }
    }

    fun doSignOut() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle(resources.getString(R.string.action_sign_out))
        builder.setMessage(resources.getString(R.string.action_sign_out_verify))
        builder.setPositiveButton(R.string.yes) { _, _ ->
            AppPreference(this).setSignIn(false)
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            this.finish()
        }
        builder.setNegativeButton(R.string.no) { _, _ -> }
        builder.show()
    }
}