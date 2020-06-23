package com.shivamkumarjha.bookstore.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.FragmentTransaction
import com.shivamkumarjha.bookstore.R
import com.shivamkumarjha.bookstore.common.AppPreference
import com.shivamkumarjha.bookstore.model.LoggedInUserView
import com.shivamkumarjha.bookstore.ui.booklist.BookListFragment
import com.shivamkumarjha.bookstore.ui.login.LoginActivity

class DashboardActivity : AppCompatActivity() {

    private lateinit var toolbar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setUpToolbar()
        getUser()
        callBookFragment()
    }

    private fun setUpToolbar() {
        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.title = resources.getString(R.string.app_name)
    }

    private fun getUser(): LoggedInUserView? {
        val bundle = intent.getBundleExtra("bundle")
        val loggedInUserView = bundle?.getParcelable<LoggedInUserView>("loggedInUserView")

        if (loggedInUserView != null)
            Toast.makeText(this, "Welcome " + loggedInUserView?.name, Toast.LENGTH_SHORT).show()
        return loggedInUserView
    }

    private fun callBookFragment() {
        supportFragmentManager.beginTransaction()
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            .replace(R.id.fragment_holder, BookListFragment())
            .commit()
    }

    fun doSignOut() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle(resources.getString(R.string.action_sign_out))
        builder.setMessage(resources.getString(R.string.action_sign_out_verify))
        builder.setPositiveButton(android.R.string.yes) { _, _ ->
            AppPreference(this).setSignIn(false)
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            this.finish()
        }
        builder.setNegativeButton(android.R.string.no) { _, _ ->
        }
        builder.show()
    }
}