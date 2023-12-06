package com.example.wastewise.ui.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.Toast
import androidx.activity.viewModels
import com.example.wastewise.BottomNavigationView
import com.example.wastewise.databinding.ActivitySplashBinding
import com.example.wastewise.ui.login.LoginActivity
import com.example.wastewise.ui.register.RegisterViewModel
import com.example.wastewise.data.Result
import com.example.wastewise.utils.Preference
import com.example.wastewise.utils.ViewModelFactory

class SplashActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashBinding

    private val splashViewModel by viewModels<SplashViewModel> {
        ViewModelFactory(applicationContext)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val sharedPref = Preference.initPref(this, "onSignIn")
        val refreshToken = sharedPref.getString("refreshToken", null)

        if (refreshToken.isNullOrEmpty()) {
            Handler(mainLooper).postDelayed({
                startActivity(Intent(this, LoginActivity::class.java))
                finish()
            }, 2000)
        } else {
            splashViewModel.refreshToken(refreshToken!!).observe(this, {
                if (it != null) {
                    when (it) {
                        is Result.Loading -> {
                            // Handle loading state if needed
                        }
                        is Result.Success -> {
                            Preference.saveToken(it.data.token, this)
                            Handler(mainLooper).postDelayed({
                                startActivity(Intent(this, BottomNavigationView::class.java))
                                finish()
                            }, 2000)
                        }
                        is Result.Error -> {
                            Toast.makeText(this, it.error, Toast.LENGTH_SHORT).show()
                            // Handle error state if needed
                        }
                    }
                }
            })
        }
    }

}