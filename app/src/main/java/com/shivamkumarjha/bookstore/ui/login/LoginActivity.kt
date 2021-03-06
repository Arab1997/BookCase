package com.shivamkumarjha.bookstore.ui.login

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.shivamkumarjha.bookstore.R
import com.shivamkumarjha.bookstore.common.AppPreference
import com.shivamkumarjha.bookstore.common.UserViewModelFactory
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
        val userRepository = UserRepository(userFile, null)
        loginViewModel =
            ViewModelProvider(this, UserViewModelFactory(userRepository))
                .get(LoginViewModel::class.java)

        // Set previous email where login was success
        emailEditText.setText(AppPreference(this).getUserEmail())
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

        loginViewModel.loginResult.observe(this@LoginActivity, Observer {
            val loginResult = it ?: return@Observer

            if (loginResult.error != null) {
                passwordEditText.error = getString(loginResult.error)
            }
            if (loginResult.success != null) {
                AppPreference(this@LoginActivity).setSignIn(true)
                Toast.makeText(this, "Добро пожаловать " + loginResult.success.name, Toast.LENGTH_SHORT)
                    .show()

                // Start Dashboard activity & pass LoggedInUser
                val intent = Intent(this, DashboardActivity::class.java)
                val bundle = Bundle()
                bundle.putParcelable("loggedInUserView", loginResult.success)
                intent.putExtra("bundle", bundle)
                startActivity(intent)
                this@LoginActivity.finish()
                setResult(Activity.RESULT_OK)
            }
        })
    }

    private fun viewListeners() {
        emailEditText.afterTextChanged { onDataChange() }
        passwordEditText.afterTextChanged { onDataChange() }
        submitButton.setOnClickListener {
            loginViewModel.onLogin(
                emailEditText.text.toString(),
                passwordEditText.text.toString()
            )
        }
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
}