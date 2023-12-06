package com.example.wastewise.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.wastewise.R
import com.example.wastewise.databinding.ActivityLogoutBinding
import com.example.wastewise.ui.login.LoginActivity
import com.example.wastewise.utils.Preference

class LogoutActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLogoutBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val context = this
        binding = ActivityLogoutBinding.inflate(layoutInflater)
        setContentView(binding.root)

        with(binding){
            binding.btnLogout.setOnClickListener {
                Preference.logOut(context)
                val intent = Intent(this@LogoutActivity, LoginActivity::class.java)
                startActivity(intent)
            }
        }
    }
}