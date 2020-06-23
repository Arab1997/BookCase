package com.shivamkumarjha.bookstore.ui.login

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.shivamkumarjha.bookstore.R
import com.shivamkumarjha.bookstore.ui.register.RegisterActivity

class LoginActivity : AppCompatActivity() {

    private lateinit var registerNowTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        initializer()
        setUpViews()
    }

    private fun initializer() {
        registerNowTextView = findViewById(R.id.tv_not_register)
    }

    private fun setUpViews() {
        registerNowTextView.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
    }
}