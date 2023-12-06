package com.example.wastewise.ui.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import com.example.wastewise.BottomNavigationView
import com.example.wastewise.R
import com.example.wastewise.data.remote.response.login.LoginResponse
import com.example.wastewise.databinding.ActivityLoginBinding
import com.example.wastewise.ui.register.RegisterActivity
import com.example.wastewise.utils.Preference
import com.example.wastewise.utils.ViewModelFactory
import com.example.wastewise.data.Result

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private val loginViewModel by viewModels<LoginViewModel> {
        ViewModelFactory(applicationContext)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.loginButton.setOnClickListener {
            val email = binding.emailEditText.text.toString()
            val password = binding.passwordEditText.text.toString()

            loginViewModel.login(email, password).observe(this, {
                if (it != null) {
                    when(it) {
                        is Result.Loading -> {
                            showLoading(true)
                        }
                        is Result.Success -> {
                            showLoading(false)
                            processLogin(it.data)
                        }
                        is Result.Error -> {
                            showLoading(false)
                            Toast.makeText(this, it.error, Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            })

        }

        binding.tvLoginDontHaveAccount.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
    }

    private fun processLogin(data: LoginResponse) {
        if (data.status == "error") {
            if (data.message != null) {
                Toast.makeText(this, data.message, Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, data.error.message, Toast.LENGTH_SHORT).show()
            }
        } else {
            Preference.saveRefreshToken(data.refreshToken, this)
            Preference.saveToken(data.token, this)
            val intent = Intent(this, BottomNavigationView::class.java)
            startActivity(intent)
        }
    }

    private fun showLoading(state: Boolean) {
        binding.pbLogin.isVisible = state
        binding.emailEditText.isInvisible = state
        binding.passwordEditText.isInvisible = state
        binding.loginButton.isInvisible = state
    }
}