package com.shivamkumarjha.bookstore.ui

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.shivamkumarjha.bookstore.R
import com.shivamkumarjha.bookstore.common.AppPreference

class SplashActivity : AppCompatActivity() {

    private val handler = Handler()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        if (AppPreference(this@SplashActivity).getSignIn()) {
            handler.postDelayed({
                val intent = Intent(this@SplashActivity, DashboardActivity::class.java)
                startActivity(intent)
                this@SplashActivity.finish()
            }, 500)
        } else {
            handler.postDelayed({
                val intent = Intent(this@SplashActivity, LoginActivity::class.java)
                startActivity(intent)
                this@SplashActivity.finish()
            }, 800)
        }
    }

    // To avoid back press
    override fun onBackPressed() {}

    override fun onDestroy() {
        handler.removeCallbacksAndMessages(null)
        super.onDestroy()
    }
}