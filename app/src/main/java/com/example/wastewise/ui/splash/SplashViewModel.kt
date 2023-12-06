package com.example.wastewise.ui.splash

import androidx.lifecycle.ViewModel
import com.example.wastewise.data.Repository

class SplashViewModel(private val repository: Repository) : ViewModel() {
    fun refreshToken(refreshToken: String) = repository.postRefreshToken(refreshToken)
}