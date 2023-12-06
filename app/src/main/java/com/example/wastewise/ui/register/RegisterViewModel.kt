package com.example.wastewise.ui.register

import androidx.lifecycle.ViewModel
import com.example.wastewise.data.Repository

class RegisterViewModel(private val repository: Repository) : ViewModel() {
    fun signUp(email: String, password: String) = repository.postSignUp(email, password)
}