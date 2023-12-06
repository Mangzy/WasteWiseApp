package com.example.wastewise.ui.login

import androidx.lifecycle.ViewModel
import com.example.wastewise.data.Repository

class LoginViewModel(private val repository: Repository) : ViewModel() {
    fun login(email: String, password: String) = repository.postLogin(email, password)
}