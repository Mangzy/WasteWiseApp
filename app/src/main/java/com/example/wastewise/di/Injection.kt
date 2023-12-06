package com.example.wastewise.di

import android.content.Context
import com.example.wastewise.data.Repository
import com.example.wastewise.data.remote.network.ApiConfig

object Injection {
    fun provideRepository(context: Context): Repository {
        val apiService = ApiConfig.getApiService(context)
        return Repository(apiService)
    }
}