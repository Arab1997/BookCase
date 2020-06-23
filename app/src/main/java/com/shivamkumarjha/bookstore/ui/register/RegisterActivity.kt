package com.shivamkumarjha.bookstore.ui.register

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.shivamkumarjha.bookstore.R
import com.shivamkumarjha.bookstore.common.afterTextChanged
import com.shivamkumarjha.bookstore.repository.UserRepository
import com.shivamkumarjha.bookstore.utility.JsonUtility
import java.io.File

class RegisterActivity : AppCompatActivity() {

    private lateinit var nameEditText: EditText
    private lateinit var emailEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var verifyPasswordEditText: EditText
    private lateinit var submitButton: Button
    private lateinit var registerViewModel: RegisterViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        initializer()
        setUpViewModel()
        viewListeners()
    }

    private fun initializer() {
        nameEditText = findViewById(R.id.register_name_id)
        emailEditText = findViewById(R.id.register_email_id)
        passwordEditText = findViewById(R.id.register_password_id)
        verifyPasswordEditText = findViewById(R.id.register_password_verify_id)
        submitButton = findViewById(R.id.register_submit_id)

        // ViewModel
        val userFile = File(filesDir, resources.getString(R.string.file_users))
        val jsonUtility = JsonUtility(userFile)
        registerViewModel =
            ViewModelProvider(this, RegisterViewModelFactory(UserRepository(jsonUtility)))
                .get(RegisterViewModel::class.java)
    }

    private fun setUpViewModel() {
        registerViewModel.registerFormState.observe(this@RegisterActivity, Observer {
            val registerState = it ?: return@Observer
            submitButton.isEnabled = registerState.isDataValid
            if (registerState.nameError != null)
                nameEditText.error = getString(registerState.nameError)
            if (registerState.emailError != null)
                emailEditText.error = getString(registerState.emailError)
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
        submitButton.setOnClickListener {
            //TODO
        }
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