package com.shivamkumarjha.bookstore.ui.login

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.shivamkumarjha.bookstore.R
import com.shivamkumarjha.bookstore.common.AppPreference
import com.shivamkumarjha.bookstore.common.afterTextChanged
import com.shivamkumarjha.bookstore.common.hideKeyboard
import com.shivamkumarjha.bookstore.repository.UserRepository
import com.shivamkumarjha.bookstore.ui.DashboardActivity
import com.shivamkumarjha.bookstore.ui.register.RegisterActivity
import java.io.File

class LoginActivity : AppCompatActivity() {

    private lateinit var emailEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var submitButton: Button
    private lateinit var registerNowTextView: TextView
    private lateinit var loginConstraintLayout: ConstraintLayout
    private lateinit var loginViewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        initializer()
        setUpViewModel()
        viewListeners()
    }

    private fun initializer() {
        emailEditText = findViewById(R.id.et_login_mail_id)
        passwordEditText = findViewById(R.id.et_login_password)
        submitButton = findViewById(R.id.bt_login)
        registerNowTextView = findViewById(R.id.tv_not_register)
        loginConstraintLayout = findViewById(R.id.login_constraint)

        // ViewModel
        val userFile = File(filesDir, resources.getString(R.string.file_users))
        loginViewModel =
            ViewModelProvider(this, LoginViewModelFactory(UserRepository(userFile)))
                .get(LoginViewModel::class.java)

    }

    private fun setUpViewModel() {
        loginViewModel.loginFormState.observe(this@LoginActivity, Observer {
            val loginState = it ?: return@Observer
            submitButton.isEnabled = loginState.isDataValid
            if (loginState.emailError != null)
                emailEditText.error = getString(loginState.emailError)
            if (loginState.emailUnregisteredError != null)
                emailEditText.error = getString(loginState.emailUnregisteredError)
            if (loginState.passwordError != null)
                passwordEditText.error = getString(loginState.passwordError)
        })
    }

    private fun viewListeners() {
        emailEditText.afterTextChanged { onDataChange() }
        passwordEditText.afterTextChanged { onDataChange() }
        submitButton.setOnClickListener { loginUser() }
        loginConstraintLayout.setOnClickListener { hideKeyboard() }
        registerNowTextView.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
    }

    private fun onDataChange() {
        loginViewModel.loginDataChanged(
            emailEditText.text.toString(),
            passwordEditText.text.toString()
        )
    }

    private fun loginUser() {
        val loginResult = loginViewModel.onLogin(
            emailEditText.text.toString(),
            passwordEditText.text.toString()
        )
        if (loginResult) {
            AppPreference(this@LoginActivity).setSignIn(true)
            val intent = Intent(this, DashboardActivity::class.java)
            startActivity(intent)
            this@LoginActivity.finish()
            return
        } else
            passwordEditText.error = resources.getString(R.string.wrong_password)
    }
}