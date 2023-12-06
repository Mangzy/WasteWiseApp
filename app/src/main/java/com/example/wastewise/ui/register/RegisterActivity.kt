package com.example.wastewise.ui.register

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract.CommonDataKinds.Email
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import com.example.wastewise.data.remote.response.register.RegisterResponse
import com.example.wastewise.databinding.ActivityRegisterBinding
import com.example.wastewise.utils.ViewModelFactory
import com.example.wastewise.data.Result
import com.example.wastewise.ui.login.LoginActivity

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding
    private val registerViewModel by viewModels<RegisterViewModel> {
        ViewModelFactory(applicationContext)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.signupButton.setOnClickListener{
            val email = binding.emailEditText.text.toString()
            val password = binding.passwordEditText.text.toString()

            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(binding.signupButton.windowToken, 0)

            registerViewModel.signUp(email, password).observe(this, {
                if (it != null) {
                    when(it) {
                        is Result.Loading -> {
                            showLoading(true)
                        }
                        is Result.Success -> {
                            showLoading(false)
                            processSignUp(it.data, email)
                            val intent = Intent(this, LoginActivity::class.java)
                            startActivity(intent)
                        }
                        is Result.Error -> {
                            showLoading(false)
                            Toast.makeText(this, it.error, Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            })


        }
    }

    private fun processSignUp(data: RegisterResponse, email: String) {
        if (data.status == "error") {
            if (data.message != null) {
                Toast.makeText(this, data.message, Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, data.error.message, Toast.LENGTH_SHORT).show()
            }
        } else {
            Toast.makeText(this, "Mail Verification sent to $email", Toast.LENGTH_SHORT).show()
        }
    }

    private fun showLoading(state: Boolean) {
        binding.pbCreateSignup.isVisible = state
        binding.emailEditText.isInvisible = state
        binding.passwordEditText.isInvisible = state
        binding.signupButton.isInvisible = state
    }
}