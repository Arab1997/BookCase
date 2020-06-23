package com.shivamkumarjha.bookstore.ui.register

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.shivamkumarjha.bookstore.R
import com.shivamkumarjha.bookstore.common.AppPreference
import com.shivamkumarjha.bookstore.common.afterTextChanged
import com.shivamkumarjha.bookstore.common.hideKeyboard
import com.shivamkumarjha.bookstore.model.User
import com.shivamkumarjha.bookstore.repository.UserRepository
import java.io.File

class RegisterActivity : AppCompatActivity() {

    private lateinit var nameEditText: EditText
    private lateinit var emailEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var verifyPasswordEditText: EditText
    private lateinit var submitButton: Button
    private lateinit var registerConstraintLayout: ConstraintLayout
    private lateinit var registerViewModel: RegisterViewModel
    private lateinit var toolbar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        initializer()
        setUpToolbar()
        setUpViewModel()
        viewListeners()
    }

    private fun initializer() {
        nameEditText = findViewById(R.id.register_name_id)
        emailEditText = findViewById(R.id.register_email_id)
        passwordEditText = findViewById(R.id.register_password_id)
        verifyPasswordEditText = findViewById(R.id.register_password_verify_id)
        submitButton = findViewById(R.id.register_submit_id)
        registerConstraintLayout = findViewById(R.id.register_layout_id)

        // ViewModel
        val userFile = File(filesDir, resources.getString(R.string.file_users))
        registerViewModel =
            ViewModelProvider(this, RegisterViewModelFactory(UserRepository(userFile)))
                .get(RegisterViewModel::class.java)
    }

    private fun setUpToolbar() {
        toolbar = findViewById(R.id.register_toolbar)
        toolbar.title = resources.getString(R.string.got_account)
        toolbar.setNavigationIcon(R.drawable.ic_back)
        toolbar.setNavigationOnClickListener {
            onBackPressed()
        }
    }

    private fun setUpViewModel() {
        registerViewModel.registerFormState.observe(this@RegisterActivity, Observer {
            val registerState = it ?: return@Observer
            submitButton.isEnabled = registerState.isDataValid
            if (registerState.nameError != null)
                nameEditText.error = getString(registerState.nameError)
            if (registerState.emailError != null)
                emailEditText.error = getString(registerState.emailError)
            if (registerState.duplicateEmailError != null)
                emailEditText.error = getString(registerState.duplicateEmailError)
            if (registerState.passwordError != null)
                passwordEditText.error = getString(registerState.passwordError)
            if (registerState.verifyPasswordError != null)
                verifyPasswordEditText.error = getString(registerState.verifyPasswordError)
        })
    }

    private fun viewListeners() {
        nameEditText.afterTextChanged { onDataChange() }
        emailEditText.afterTextChanged { onDataChange() }
        passwordEditText.afterTextChanged { onDataChange() }
        verifyPasswordEditText.afterTextChanged { onDataChange() }
        submitButton.setOnClickListener { submitUser() }
        registerConstraintLayout.setOnClickListener { hideKeyboard() }
    }

    private fun submitUser() {
        registerViewModel.onSubmitClick(
            User(
                AppPreference(this).newUserId(),
                nameEditText.text.toString(),
                emailEditText.text.toString(),
                passwordEditText.text.toString()
            )
        )
        hideKeyboard()
        Toast.makeText(
            this@RegisterActivity,
            resources.getString(R.string.user_registered),
            Toast.LENGTH_SHORT
        ).show()
        onBackPressed()
    }

    private fun onDataChange() {
        registerViewModel.registerDataChanged(
            nameEditText.text.toString(),
            emailEditText.text.toString(),
            passwordEditText.text.toString(),
            verifyPasswordEditText.text.toString()
        )
    }
}