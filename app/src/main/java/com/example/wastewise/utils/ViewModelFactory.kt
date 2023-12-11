package com.example.wastewise.utils

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.wastewise.di.Injection
import com.example.wastewise.ui.booklet.BookletViewModel
import com.example.wastewise.ui.detail__article.DetailArticleViewModel
import com.example.wastewise.ui.home.HomeViewModel
import com.example.wastewise.ui.login.LoginViewModel
import com.example.wastewise.ui.profile.ProfileViewModel
import com.example.wastewise.ui.register.RegisterViewModel
import com.example.wastewise.ui.splash.SplashViewModel

class ViewModelFactory(private val context: Context) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(RegisterViewModel::class.java) -> {
                RegisterViewModel(Injection.provideRepository(context)) as T
            }
            modelClass.isAssignableFrom(LoginViewModel::class.java) -> {
                LoginViewModel(Injection.provideRepository(context)) as T
            }
            modelClass.isAssignableFrom(ProfileViewModel::class.java) -> {
                ProfileViewModel(Injection.provideRepository(context)) as T
            }
            modelClass.isAssignableFrom(SplashViewModel::class.java) -> {
                SplashViewModel(Injection.provideRepository(context)) as T
            }
            modelClass.isAssignableFrom(HomeViewModel::class.java) -> {
                HomeViewModel(Injection.provideRepository(context)) as T
            }
            modelClass.isAssignableFrom(DetailArticleViewModel::class.java) -> {
                DetailArticleViewModel(Injection.provideRepository(context)) as T
            }
            modelClass.isAssignableFrom(BookletViewModel::class.java) -> {
                BookletViewModel(Injection.provideRepository(context)) as T
            }
            else -> throw IllegalArgumentException("Unknown ViewModel Class")
        }
    }
}